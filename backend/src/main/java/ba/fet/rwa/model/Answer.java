package ba.fet.rwa.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "Answer")
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@Column(name = "ID")
  @JsonProperty("id")
  private Long id = null;

	@Column(name = "TEXT")
  @JsonProperty("text")
  private String text = null;

	@Column(name = "CORRECT")
  @JsonProperty("correct")
  private Boolean correct = null;
  
  @JoinColumn(name = "QUESTION")
  @ManyToOne
  @JsonProperty("question")
  private Question question = null;

  public Answer id(Long id) {
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

  public Answer text(String text) {
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

  public Answer correct(Boolean correct) {
    this.correct = correct;
    return this;
  }

  @JsonProperty("correct")
  public Boolean isCorrect() {
    return correct;
  }

  public void setCorrect(Boolean correct) {
    this.correct = correct;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Answer answer = (Answer) o;
    return Objects.equals(this.id, answer.id) &&
        Objects.equals(this.text, answer.text) &&
        Objects.equals(this.correct, answer.correct);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, text, correct);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Answer {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    correct: ").append(toIndentedString(correct)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
