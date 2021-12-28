package zaman.example.cricnews.dto;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "rss")
public class Rss {
    @JacksonXmlProperty(localName = "channel")
    public Channel channel;
    @JacksonXmlProperty(localName = "version")
    public double version;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }
}