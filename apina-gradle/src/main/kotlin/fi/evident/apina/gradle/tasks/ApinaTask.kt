package fi.evident.apina.gradle.tasks

import fi.evident.apina.ApinaProcessor
import fi.evident.apina.java.reader.Classpath
import fi.evident.apina.model.settings.EnumMode
import fi.evident.apina.model.settings.Platform
import fi.evident.apina.spring.EndpointParameterNameNotDefinedException
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.util.GFileUtils.writeFile
import java.io.File
import java.util.*
import kotlin.properties.Delegates

open class ApinaTask : DefaultTask() {

    @get:OutputFile
    var target: File by Delegates.notNull()

    @get:InputFiles
    var classpath: FileCollection by Delegates.notNull()

    @get:Input
    var blackBoxClasses: List<String> = ArrayList()

    @get:Input
    var imports: Map<String, List<String>> = HashMap()

    @get:Input
    var platform = Platform.ANGULAR2

    @get:Input
    var enumMode = EnumMode.ENUM

    @TaskAction
    fun generateTypeScript() {
        try {
            val myClasspath = Classpath()
            for (file in classpath)
                myClasspath.addRoot(file.toPath())

            val processor = ApinaProcessor(myClasspath)

            processor.settings.enumMode = enumMode
            processor.settings.platform = platform

            for (pattern in blackBoxClasses)
                processor.settings.blackBoxClasses.addPattern(pattern)

            for ((key, value) in imports)
                processor.settings.addImport(key, value)

            val output = processor.process()

            writeFile(output, target, "UTF-8")

        } catch (e: EndpointParameterNameNotDefinedException) {
            logger.error("{}\nConsider adding 'compileJava { options.compilerArgs = ['-parameters'] }' to your build file.", e.message)
            throw e
        }
    }

    companion object {
        val GENERATE_API_CLIENT_TASK_NAME = "apina"
    }
}
