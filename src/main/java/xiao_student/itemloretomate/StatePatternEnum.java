package xiao_student.itemloretomate;


public enum StatePatternEnum {

    DAMAGE(".*<攻击力> : [0-9]*$"),
    HEALTH(".*<生命值> : [0-9]*$"),
    CRIT(".*<暴击率> : [0-9]*%$"),
    CRITDAMAGE(".*<暴击伤害> : [0-9]*%$"),
    SUCKBLOOD(".*<吸血> : [0-9]*%$"),

    ;
    private String pattern;

    StatePatternEnum(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
