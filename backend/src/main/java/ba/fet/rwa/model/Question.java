package ba.fet.rwa.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

public class Question {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("order")
  private Integer order = null;

  @JsonProperty("text")
  private String text = null;

  @JsonProperty("time")
  private Integer time = null;

  @JsonProperty("points")
  private Integer points = null;

  @JsonProperty("answers")
  private List<Answer> answers = null;

  public Question id(Long id) {
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

  public Question order(Integer order) {
    this.order = order;
    return this;
  }

  @JsonProperty("order")
  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public Question text(String text) {
    this.text = text;
    return this;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Question time(Integer time) {
    this.time = time;
    return this;
  }

  @JsonProperty("time")
  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

  public Question points(Integer points) {
    this.points = points;
    return this;
  }

  @JsonProperty("points")
  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }

  public Question answers(List<Answer> answers) {
    this.answers = answers;
    return this;
  }

  public Question addAnswersItem(Answer answersItem) {
    if (this.answers == null) {
      this.answers = new ArrayList<Answer>();
    }
    this.answers.add(answersItem);
    return this;
  }

  @JsonProperty("answers")
  @Valid
  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Question question = (Question) o;
    return Objects.equals(this.id, question.id) &&
        Objects.equals(this.order, question.order) &&
        Objects.equals(this.text, question.text) &&
        Objects.equals(this.time, question.time) &&
        Objects.equals(this.points, question.points) &&
        Objects.equals(this.answers, question.answers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, order, text, time, points, answers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Question {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    points: ").append(toIndentedString(points)).append("\n");
    sb.append("    answers: ").append(toIndentedString(answers)).append("\n");
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
