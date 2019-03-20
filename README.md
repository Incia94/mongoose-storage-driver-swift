[![Gitter chat](https://badges.gitter.im/emc-mongoose.png)](https://gitter.im/emc-mongoose)
[![Issue Tracker](https://img.shields.io/badge/Issue-Tracker-red.svg)](https://mongoose-issues.atlassian.net/projects/GOOSE)
[![CI status](https://gitlab.com/emc-mongoose/mongoose-storage-driver-swift/badges/master/pipeline.svg)](https://gitlab.com/emc-mongoose/mongoose-storage-driver-swift/commits/master)
[![Tag](https://img.shields.io/github/tag/emc-mongoose/mongoose-storage-driver-swift.svg)](https://github.com/emc-mongoose/mongoose-storage-driver-swift/tags)
[![Maven metadata URL](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/github/emc-mongoose/mongoose-storage-driver-swift/maven-metadata.xml.svg)](http://central.maven.org/maven2/com/github/emc-mongoose/mongoose-storage-driver-swift)
[![Sonatype Nexus (Releases)](https://img.shields.io/nexus/r/http/oss.sonatype.org/com.github.emc-mongoose/mongoose-storage-driver-swift.svg)](http://oss.sonatype.org/com.github.emc-mongoose/mongoose-storage-driver-swift)
[![Docker Pulls](https://img.shields.io/docker/pulls/emcmongoose/mongoose-storage-driver-swift.svg)](https://hub.docker.com/r/emcmongoose/mongoose-storage-driver-swift/)

# Swift Storage Driver

## 1. Features

* API version: 1.0
* Authentification:
    * Uid/secret key pair for auth token operations
    * Auth token
* SSL/TLS
* Item types:
    * `data` (--> "object")
    * `path` (--> "container")
    * `token` (--> "auth token")
* Automatic auth token creation on demand
* Automatic destination path creation on demand
* Path listing input (with JSON response payload)
* Data item operation types:
    * `create`
        * [copy](../../../../../../doc/design/copy_mode/README.md)
        * [Dynamic Large Objects](../../../../../../src/main/java/com/emc/mongoose/base/item/op/composite/README.md)
    * `read`
        * full
        * random byte ranges
        * fixed byte ranges
        * content verification
    * `update`
        * full (overwrite)
        * random byte ranges
        * fixed byte ranges (with append mode)
    * `delete`
    * `noop`
* Token item operation types:
    * `create`
    * `noop`
* Path item operation types:
    * `create`
    * `read`
    * `delete`
    * `noop`

## 2. Usage

```bash
java -jar mongoose-<VERSION>.jar \
    --storage-driver-type=swift \
    --storage-namespace=ns1 \
    ...
```

### 2.1. Configuration Reference

| Name                                           | Type         | Default Value    | Description                                      |
|:-----------------------------------------------|:-------------|:-----------------|:-------------------------------------------------|
| storage-net-http-versioning                    | Flag | false | Specifies whether the versioning storage feature is used or not

### 2.2. Notes

* A **container** may be specified with `item-input-path` either `item-output-path` configuration option
* The default storage namespace value (`null`) won't work, specify the correct value explicitly
* DLO creation should be enabled using the `item-data-ranges-threshold` configuration option
* [Defect GOOSE-1316](https://mongoose-issues.atlassian.net/browse/GOOSE-1316): unable to verify the response content in
 the case of multiple byte ranges read
* The storage port is set to 9020 by default for the docker image
