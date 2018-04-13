package org.apache.commons.cli;

public class AlreadySelectedException extends ParseException {
    private OptionGroup group;
    private Option option;

    public AlreadySelectedException(String str) {
        super(str);
    }

    public AlreadySelectedException(OptionGroup optionGroup, Option option) {
        this(new StringBuffer().append("The option '").append(option.getKey()).append("' was specified but an option from this group ").append("has already been selected: '").append(optionGroup.getSelected()).append("'").toString());
        this.group = optionGroup;
        this.option = option;
    }

    public OptionGroup getOptionGroup() {
        return this.group;
    }

    public Option getOption() {
        return this.option;
    }
}
