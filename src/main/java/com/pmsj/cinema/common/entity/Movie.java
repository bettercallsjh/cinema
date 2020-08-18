package com.pmsj.cinema.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
/*import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;*/
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@ToString
@Data
/*@Document(indexName = "cinema",type = "movie",shards = 1,replicas = 0)*/
public class Movie {
    @Id
    private Integer movieId;

/*    @Field(type = FieldType.Text,analyzer = "ik_max_word")*/
    private String movieName;

/*    @Field(type = FieldType.Keyword)*/
    @JsonFormat(pattern = "yyyy年MM月dd日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date movieReleaseTime;

/*    @Field(type = FieldType.Keyword)*/
    private String movieArea;

/*    @Field(type = FieldType.Keyword)*/
    private String movieInfo;

/*    @Field(type = FieldType.Keyword)*/
    private String movieImg;

/*    @Field(type = FieldType.Text,analyzer = "ik_max_word")*/
    private String movieEname;

/*    @Field(type = FieldType.Keyword)*/
    private Float movieScore;

/*    @Field(type = FieldType.Keyword)*/
    private Integer movieStatus;

/*    @Field(type = FieldType.Keyword)*/
    private Integer movieCount;

/*    @Field(type = FieldType.Keyword)*/
    private Integer movieBoxOffice;

/*    @Field(type = FieldType.Keyword)*/
    private String movieTime;

/*    @Field(type = FieldType.Keyword)*/
    private Integer movieWantCount;

//    @Field(type = FieldType.Keyword)
    private String types[];

    private List<MovieTpye> movieTpyeList;

//    @Field(type = FieldType.Keyword)
    private Integer typeId;
//    @Field(type = FieldType.Keyword)
    private Integer paixu;

    private List<Cast> actor;


//    private
    private List<Cast> cast;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return Objects.equals(movieName, movie.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName);
    }




}