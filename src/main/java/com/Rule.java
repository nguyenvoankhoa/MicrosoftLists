package com;

import com.column.IColumn;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoublePredicate;

@Setter
@Getter
public class Rule {
    private IColumn<Object> column;
    private Condition condition;
    private double value;
    private static final Map<Condition, DoublePredicate> conditionMap = new HashMap<>();

    public Rule(IColumn<Object> column, Condition condition, double value) {
        this.column = column;
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
