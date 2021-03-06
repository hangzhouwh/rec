package cn.edu.zucc.syx.rec.entity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


public class ArtistsTags {
    // 歌手标签
    @Field(type = FieldType.Keyword)
    private String tag;

    // 标签频率
    @Field(type = FieldType.Float)
    private Float freq;

    // 标签权重
    @Field(type = FieldType.Float)
    private Float weight;

    public ArtistsTags() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Float getFreq() {
        return freq;
    }

    public void setFreq(Float freq) {
        this.freq = freq;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public ArtistsTags(String tag, Float freq, Float weight) {
        this.tag = tag;
        this.freq = freq;
        this.weight = weight;
    }
}
