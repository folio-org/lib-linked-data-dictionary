# lib-linked-data-dictionary
© 2024 EBSCO Information Services.

This software is distributed under the terms of the Apache License, Version 2.0.
See the file "[LICENSE](LICENSE)" for more information.
## Purpose
This Java library stores:
1) The Linked Data vocabulary as Java enums: [types](https://github.com/folio-org/lib-linked-data-dictionary/blob/master/src/main/java/org/folio/ld/dictionary/ResourceTypeDictionary.java), [properties](https://github.com/folio-org/lib-linked-data-dictionary/blob/master/src/main/java/org/folio/ld/dictionary/PropertyDictionary.java) and [predicates](https://github.com/folio-org/lib-linked-data-dictionary/blob/master/src/main/java/org/folio/ld/dictionary/PredicateDictionary.java).
2) The Linked Data [resource model](https://github.com/folio-org/lib-linked-data-dictionary/blob/master/src/main/java/org/folio/ld/dictionary/model/Resource.java) with [hashing function](https://github.com/folio-org/lib-linked-data-dictionary/blob/master/src/main/java/org/folio/ld/dictionary/util/HashUtils.java) (used for a Linked Data resource ID generation).
## Compiling
```bash
mvn clean install
```
## Using the library
The library can be used as a source of Linked Data vocabulary and resource model.

First is essential if it's needed to build a resource graph compatible with the Linked Data resource graph in terms of vocabulary.

Second is a Java API model for runtime interaction with the mod-linked-data (example is the [lib-linked-data-marc4ld](https://github.com/folio-org/lib-linked-data-marc4ld) library).
## Download and configuration
The built artifacts for this module are available. See [configuration](https://dev.folio.org/download/artifacts/) for repository access.
