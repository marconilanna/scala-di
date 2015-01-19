Scala DI
========

[![Build Status](https://travis-ci.org/marconilanna/scala-di.svg)](https://travis-ci.org/marconilanna/scala-di)

Simple, typesafe, boilerplate-free^H^H^H^Hreduced dependency injection for Scala.

Dependency injection (DI) is a fancy name for passing arguments to constructors or methods.
DI does not require anything especial and over-complicated.
DI is just using parameters.

All patterns have safe and unsafe variants.
Unsafe means that a default value for the dependency is provided automatically, which may then be overridden as needed.
Safe means that the dependency is exposed in the API signature and has to always be explicitly provided.
Unsafe makes the wiring simpler and easier at client side, while safe makes it, well, safer. :-)

License
-------

Copyright 2014 Marconi Lanna

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
