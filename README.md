# JavaAstraDropIn
Quick Java app to connect to Astra DB and list the available keyspaces via the Restful API.

## To run

Clone this repo:

    git clone git@github.com:aar0np/JavaAstraDropIn.git

Build:

    mvn clean install
    
Define environment vars:

    export ASTRA_TOKEN=AstraCS:a0d7c0b8da7blahblahtokengoeshereblahblahbc08cb78080870a7cd
    export ASTRA_DB_REGION=us-east9
    export ASTRA_DB_ID=a1345678-1234-5678-987a-58a84581368f

Run:

    java -jar target/javaastradropin-0.0.1-SNAPSHOT-spring-boot.jar
