package org.graylog.challenge.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude
public class ClientLogMessage extends GelfHttpMessage {

    private String clientDeviceType;
    private String clientIP;
    private String clientIPClass;
    private Integer clientStatus;
    private Integer clientRequestBytes;
    private String clientRequestReferer;
    private String clientRequestURI;
    private String clientRequestUserAgent;
    private Integer clientSrcPort;
    private String edgeServerIP;
    private Double edgeStartTimestamp;
    private String destinationIP;
    private Integer originResponseBytes;
    private Double originResponseTime;

    @JsonProperty("_ClientDeviceType")
    public String getClientDeviceType() {
        return clientDeviceType;
    }

    @JsonProperty("ClientDeviceType")
    public ClientLogMessage setClientDeviceType(String clientDeviceType) {
        this.clientDeviceType = clientDeviceType;
        return this;
    }

    @JsonProperty("_ClientIP")
    public String getClientIP() {
        return clientIP;
    }

    @JsonProperty("ClientIP")
    public ClientLogMessage setClientIP(String clientIP) {
        this.clientIP = clientIP;
        return this;
    }

    @JsonProperty("_ClientIPClass")
    public String getClientIPClass() {
        return clientIPClass;
    }

    @JsonProperty("ClientIPClass")
    public ClientLogMessage setClientIPClass(String clientIPClass) {
        this.clientIPClass = clientIPClass;
        return this;
    }

    @JsonProperty("_ClientStatus")
    public Integer getClientStatus() {
        return clientStatus;
    }

    @JsonProperty("ClientStatus")
    public ClientLogMessage setClientStatus(Integer clientStatus) {
        this.clientStatus = clientStatus;
        return this;
    }

    @JsonProperty("_ClientRequestBytes")
    public Integer getClientRequestBytes() {
        return clientRequestBytes;
    }

    @JsonProperty("ClientRequestBytes")
    public ClientLogMessage setClientRequestBytes(Integer clientRequestBytes) {
        this.clientRequestBytes = clientRequestBytes;
        return this;
    }

    @JsonProperty("_ClientRequestReferer")
    public String getClientRequestReferer() {
        return clientRequestReferer;
    }

    @JsonProperty("ClientRequestReferer")
    public ClientLogMessage setClientRequestReferer(String clientRequestReferer) {
        this.clientRequestReferer = clientRequestReferer;
        return this;
    }

    @JsonProperty("_ClientRequestURI")
    public String getClientRequestURI() {
        return clientRequestURI;
    }

    @JsonProperty("ClientRequestURI")
    public ClientLogMessage setClientRequestURI(String clientRequestURI) {
        this.clientRequestURI = clientRequestURI;
        return this;
    }

    @JsonProperty("_ClientRequestUserAgent")
    public String getClientRequestUserAgent() {
        return clientRequestUserAgent;
    }

    @JsonProperty("ClientRequestUserAgent")
    public ClientLogMessage setClientRequestUserAgent(String clientRequestUserAgent) {
        this.clientRequestUserAgent = clientRequestUserAgent;
        return this;
    }

    @JsonProperty("_ClientSrcPort")
    public Integer getClientSrcPort() {
        return clientSrcPort;
    }

    @JsonProperty("ClientSrcPort")
    public ClientLogMessage setClientSrcPort(Integer clientSrcPort) {
        this.clientSrcPort = clientSrcPort;
        return this;
    }

    @JsonProperty("_EdgeServerIP")
    public String getEdgeServerIP() {
        return edgeServerIP;
    }

    @JsonProperty("EdgeServerIP")
    public ClientLogMessage setEdgeServerIP(String edgeServerIP) {
        this.edgeServerIP = edgeServerIP;
        return this;
    }

    @JsonProperty("_EdgeStartTimestamp")
    public Double getEdgeStartTimestamp() {
        return edgeStartTimestamp;
    }

    @JsonProperty("EdgeStartTimestamp")
    public ClientLogMessage setEdgeStartTimestamp(Double edgeStartTimestamp) {
        this.edgeStartTimestamp = edgeStartTimestamp;
        return this;
    }

    @JsonProperty("_DestinationIP")
    public String getDestinationIP() {
        return destinationIP;
    }

    @JsonProperty("DestinationIP")
    public ClientLogMessage setDestinationIP(String destinationIP) {
        this.destinationIP = destinationIP;
        return this;
    }

    @JsonProperty("_OriginResponseBytes")
    public Integer getOriginResponseBytes() {
        return originResponseBytes;
    }

    @JsonProperty("OriginResponseBytes")
    public ClientLogMessage setOriginResponseBytes(Integer originResponseBytes) {
        this.originResponseBytes = originResponseBytes;
        return this;
    }

    @JsonProperty("_OriginResponseTime")
    public Double getOriginResponseTime() {
        return originResponseTime;
    }

    @JsonProperty("OriginResponseTime")
    public ClientLogMessage setOriginResponseTime(Double originResponseTime) {
        this.originResponseTime = originResponseTime;
        return this;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
