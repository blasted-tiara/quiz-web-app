package ba.fet.rwa.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

public class Quiz {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("picUrl")
  private String picUrl = null;

  @JsonProperty("creator")
  private User creator = null;

  @JsonProperty("questions")
  private List<Question> questions = null;

  public Quiz id(Long id) {
    this.id = id;
    return this;
  }

  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Quiz title(String title) {
    this.title = title;
    return this;
  }

  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Quiz picUrl(String picUrl) {
    this.picUrl = picUrl;
    return this;
  }

  @JsonProperty("picUrl")
  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  public Quiz creator(User creator) {
    this.creator = creator;
    return this;
  }

  @JsonProperty("creator")
  @Valid
  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  public Quiz questions(List<Question> questions) {
    this.questions = questions;
    return this;
  }

  public Quiz addQuestionsItem(Question questionsItem) {
    if (this.questions == null) {
      this.questions = new ArrayList<Question>();
    }
    this.questions.add(questionsItem);
    return this;
  }

  @JsonProperty("questions")
  @Valid
  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Quiz quiz = (Quiz) o;
    return Objects.equals(this.id, quiz.id) &&
        Objects.equals(this.title, quiz.title) &&
        Objects.equals(this.picUrl, quiz.picUrl) &&
        Objects.equals(this.creator, quiz.creator) &&
        Objects.equals(this.questions, quiz.questions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, picUrl, creator, questions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Quiz {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    picUrl: ").append(toIndentedString(picUrl)).append("\n");
    sb.append("    creator: ").append(toIndentedString(creator)).append("\n");
    sb.append("    questions: ").append(toIndentedString(questions)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
