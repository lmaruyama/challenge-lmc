# Graylog Tech Challenge by Leonardo Maruyama de Carvalho

## Input file template
The file must contain a valid JSON object per line, and must be according to the following template

| Key                     | Value    |
|-------------------------|----------|
| ClientDeviceType        | `String` |
| ClientIPClass           | `String` |
| ClientStatus            | `number` |
| ClientRequestBytes      | `number` |
| ClientRequestReferer    | `String` |
| ClientRequestUserAgent  | `String` |
| ClientSrcPort           | `number` |
| EdgeServerIP            | `String` |
| EdgeStartTimestamp      | `number` |
| DestinationIP           | `String` |
| OriginResponseBytes     | `number` |
| OriginResponseTime      | `number` |

Example of a valid line:
```
{"ClientDeviceType": "desktop","ClientIP": "192.168.87.52","ClientIPClass": "noRecord","ClientStatus": 403, "ClientRequestBytes": 889,"ClientRequestReferer": "graylog.org","ClientRequestURI": "/search","ClientRequestUserAgent": "Mozilla/5.0 (compatible, MSIE 11, Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko","ClientSrcPort":122,"EdgeServerIP": "10.0.151.71","EdgeStartTimestamp": 1576929197,"DestinationIP": "172.16.153.30","OriginResponseBytes": 821,"OriginResponseTime": 337000000}
```

## Running the application

1. Creating the executable jar

   1. ```mvn clean package```

2. Running

   1. ``` ./challenge-lmc-0.0.1-SNAPSHOT.jar <protocol>://<server>[:port]/<api> /path/to/file.txt```
   2. ``` ./challenge-lmc-0.0.1-SNAPSHOT.jar http://0.0.0.0:12201/gelf data/sample-messages.txt```
    
