package com;

import lombok.Getter;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.DoublePredicate;

@Setter
@Getter
public class Rule {
    private Condition condition;
    private double value;
    private Map<Condition, DoublePredicate> conditionMap = new EnumMap<>(Condition.class);

    public Rule(Condition condition, double value) {
        this.condition = condition;
        this.value = value;
        initializeConditionMap();
    }

    private void initializeConditionMap() {
        conditionMap.put(Condition.GREATER_THAN, v -> v > value);
        conditionMap.put(Condition.LESS_THAN, v -> v < value);
        conditionMap.put(Condition.EQUAL_TO, v -> v == value);
        conditionMap.put(Condition.NOT_EQUAL, v -> v != value);
    }

    public boolean evaluate(double columnValue) {
        DoublePredicate predicate = conditionMap.get(condition);
        return predicate.test(columnValue);
    }
}
