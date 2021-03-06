## 0.7.1 (2017-01-20)

### Improvements

- Unwrap logical return type from a possible wrapper before analyzing it. That is, interpret 
  methods returning `ResponseEntity<T>`, `HttpEntity<T>` or `Callable<T>` as methods returning just `T`.

### Fixes

- Improvements to handling some obscure generic types.

## 0.7.0 (2017-01-16)

### Improvements

- Support for using string union types (`type MyEnum = "FOO" | "BAR" | "BAZ"`) instead of `enum`-types
  for representing enums in TypeScript.

### Other changes

- Built against Gradle 3.3

## 0.6.5 (2017-01-10)

### Fixes

- Fix issue with loading resources

## 0.6.4 (2017-01-10)

### Improvements

- Support reading nested libraries from JAR/WAR-archives.
- Support serialization of maps with wildcard parameters.
- Print warning instead of failing if there are invalid classpath entries.

## 0.6.3 (2017-01-02)

### Fixes

- New alias resolution broke attributes without aliases.

## 0.6.2 (2017-01-02)

### Improvements

- More complete alias resolution for Spring.

## 0.6.1 (2017-01-01)

### Improvements

- Support @AliasFor without explicit attribute name. Use the name of the original attribute 
  if @AliasFor does not specify a name.

## 0.6.0 (2017-01-01)

### Improvements

- Add support for Spring's meta-annotations and `@AliasFor` when resolving annotations. 
  ([#30](https://github.com/EvidentSolutions/apina/issues/30))

### Other changes

- Converted most of the codebase to Kotlin. 

## 0.5.3 (2016-12-21)

### Improvements

- Create nullable types in TypeScript output if `@Nullable` or `Optional<T>` is use in Jackson classes.

## 0.5.2 (2016-12-19)

### Breaking changes

- Remove automatic `.share()` calls from returned `Observable`s in Angular 2 backend. Instead, return
  the `Observable` exactly the same way that Angular's `Http` does, letting caller decide appropriate
  strategy for sharing (if needed).

## 0.5.1 (2016-11-15)

### Fixes

- Provide default value for platform when using Gradle.

## 0.5.0 (2016-11-15)

### Improvements

- Add support for Angular 2.

### Breaking changes

- Code is now generated for Angular 2 by default, you need to specify `framework = 'angular1'` 
  to build for AngularJS.

## 0.4.3 (2016-04-21)

### Improvements

- Support reading parameter names from debug information.

## 0.4.2 (2016-01-07)

### Improvements

- TSLint warnings are disabled for the generated TypeScript file so that it can be included in
  a project regardless of TSLint configuration settings.

## 0.4.1 (2015-11-27)

### New features

- Support type parameters in inheritance ([#27](https://github.com/EvidentSolutions/apina/issues/27))
- Support overriding ignores in subclasses ([#26](https://github.com/EvidentSolutions/apina/issues/26))

### Improvements

- Fail fast if endpoint parameter name can't be resolved ([#17](https://github.com/EvidentSolutions/apina/issues/17))
- Support @RequestParam(name=...) ([#28](https://github.com/EvidentSolutions/apina/issues/28))

## 0.4.0 (2015-10-29)

### New features

- Translate Java enums to TypeScript enums and register serializers for them. ([#18](https://github.com/EvidentSolutions/apina/issues/18))

## 0.3.2 (2015-10-23)

### Bug fixes

- If class is found multiple times from classpath, ignore the redefinitions and just log them.

## 0.3.1 (2015-10-23)

### New features

- Support inherited fields when translating data types. Inheritance is not exposed in the
  generated API, but rather everything is flattened to single type.
  ([#23](https://github.com/EvidentSolutions/apina/issues/23))

### Bug fixes

- Fail fast if duplicate class names are detected. ([#19](https://github.com/EvidentSolutions/apina/issues/19))

## 0.3.0 (2015-08-19)

### New features

- Support translation of optional types (`Optional<T>`, `OptionalInt`, `OptionalLong` and `OptionalDouble`).
  ([#15](https://github.com/EvidentSolutions/apina/issues/15))
- Support configuring base URL for API calls. ([#13](https://github.com/EvidentSolutions/apina/issues/13))
- Use black-box translation for classes with `@JsonValue` ([#11](https://github.com/EvidentSolutions/apina/issues/11))
- Support importing classes to be used instead of generated classes.

## Breaking changes

- Change to Angular context: expose `apinaSerializationConfig` as `apinaConfig` instead.

## 0.2.2 (2015-06-15)

### Bug fixes

- Support parsing method descriptors with differing argument counts for 
  generic and non-generic signatures. These are not common, yet are present
  in some legacy class files.

## 0.2.1 (2015-06-10)

### Bug fixes

- Parse Spring path patterns with braces in regular expressions properly. (e.g. `/{id:[0-9a-zA-Z]{16}}`).
  ([#14](https://github.com/EvidentSolutions/apina/issues/14))

## 0.2.0 (2015-06-08)

### New features

- Write types as classes instead of interfaces. This means that they can be instantiated with a constructor
  to get an instance with all properties.
- Expose `Support.EndpointContext` as angular-service `apinaEndpointContext` and `Support.SerializationConfig` as
  `apinaSerializationConfig`. This allows us to override serializers.
  ([#3](https://github.com/EvidentSolutions/apina/issues/3))
- Expose `apina.endpoints` module that directly binds all endpoint groups to angular module so that we don't need 
  to inject `endpointGroups.` The keys are available as constants of form `Endpoints.Foo.KEY`.
  ([#7](https://github.com/EvidentSolutions/apina/issues/7))

### Breaking changes

- Generated code is simplified by translating endpoint groups directly to classes. The types are now named 
 `Endpoints.Foo` instead of `Endpoints.Foo`. Endpoint group properties of `Endpoints.IEndpointGroups` now begin
  with lowercase letter, e.g. `endpointGroups.Foo` is now `endpointGroups.foo`. 
