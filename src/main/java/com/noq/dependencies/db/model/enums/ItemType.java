package com.noq.dependencies.db.model.enums;

public enum ItemType {
    Biryani("Biryani"),Kebab_Festival("Kebab_Festival"),Starters("Starters"),
    Main_Course("Main_Course"),Combos("Combos"),Dessert("Dessert"),
    Beverages("Beverages"),Tiffins("Tiffins"),INVALID("invalid");

    private String value;

    ItemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static ItemType fromString(String str){
        for(ItemType m : ItemType.values()){
            if(m.value.equalsIgnoreCase(str)){
                return m;
            }
        }
        return ItemType.INVALID;
    }
}
