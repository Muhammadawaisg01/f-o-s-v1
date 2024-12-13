package com.fossm.contentservice.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContentCategory {
  ADULTS("Adults"),
  CLUBBING("Clubbing"),
  COFFEE("Coffee"),
  DISCOUNTS("Discounts"),
  EDUCATION("Education"),
  FASHION("Fashion"),
  FITNESS("Fitness"),
  FOOD("Food"),
  GAMING("Gaming"),
  HEALTH("Health"),
  HOLLYWOOD("Hollywood"),
  LEARNING("Learning"),
  MODELS("Models"),
  MOVIES("Movies"),
  NEWS("News"),
  PROMOTIONS("Promotions"),
  SALES("Sales"),
  SHOPPING("Shopping"),
  SOCIAL("Social"),
  SPORTS("Sports"),
  STARS("Stars"),
  STUDENTS("Students"),
  TECHNOLOGY("Technology"),
  TRAVEL("Travel");

  private final String displayedName;
}
