package com.noq.dependencies.db.model.enums;

public enum MealType {
    breakfast("breakfast"),lunch("lunch"),snacks("snacks"),dinner("dinner"), INVALID("invalid");

    private String value;

    MealType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static MealType fromString(String str){
        for(MealType m : MealType.values()){
            if(m.value.equalsIgnoreCase(str)){
                return m;
            }
        }
        return MealType.INVALID;
    }
}
