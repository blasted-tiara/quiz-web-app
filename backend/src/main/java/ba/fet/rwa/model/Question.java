package ba.fet.rwa.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

@Table(name = "Question")
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@Column(name = "ID")
  @JsonProperty("id")
  private Long id = null;

	@Column(name = "ORDER_NUMBER")
  @JsonProperty("orderNumber")
  private Integer orderNumber = null;

	@Column(name = "TEXT")
  @JsonProperty("text")
  private String text = null;

	@Column(name = "TIME")
  @JsonProperty("time")
  private Integer time = null;

	@Column(name = "POINTS")
  @JsonProperty("points")
  private Integer points = null;

  @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "question")
  @JsonProperty("answers")
  private List<Answer> answers = null;
  
  @JoinColumn(name = "QUIZ")
  @ManyToOne
  @JsonProperty("quiz")
  @JsonBackReference
  Quiz quiz = null;

  public void setOrderNumber(Integer orderNumber) {
    this.orderNumber = orderNumber;
  }

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

  public Question order(Integer orderNumber) {
    this.orderNumber = orderNumber;
    return this;
  }

  @JsonProperty("orderNumber")
  public Integer getOrderNumber() {
    return orderNumber;
  }

  public void setOrder(Integer orderNumber) {
    this.orderNumber = orderNumber;
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
        Objects.equals(this.orderNumber, question.orderNumber) &&
        Objects.equals(this.text, question.text) &&
        Objects.equals(this.time, question.time) &&
        Objects.equals(this.points, question.points) &&
        Objects.equals(this.answers, question.answers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, orderNumber, text, time, points, answers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Question {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    orderNumber: ").append(toIndentedString(orderNumber)).append("\n");
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

  public Quiz getQuiz() {
    return quiz;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }
}
