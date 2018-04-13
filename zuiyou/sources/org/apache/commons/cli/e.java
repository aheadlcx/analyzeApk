package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

public abstract class e implements a {
    protected CommandLine a;
    private Options b;
    private List c;

    protected abstract String[] b(Options options, String[] strArr, boolean z);

    protected void a(Options options) {
        this.b = options;
        this.c = new ArrayList(options.getRequiredOptions());
    }

    protected Options a() {
        return this.b;
    }

    protected List b() {
        return this.c;
    }

    public CommandLine a(Options options, String[] strArr, boolean z) throws ParseException {
        return a(options, strArr, null, z);
    }

    public CommandLine a(Options options, String[] strArr, Properties properties, boolean z) throws ParseException {
        int i = 0;
        for (Option clearValues : options.helpOptions()) {
            clearValues.clearValues();
        }
        a(options);
        this.a = new CommandLine();
        if (strArr == null) {
            strArr = new String[0];
        }
        ListIterator listIterator = Arrays.asList(b(a(), strArr, z)).listIterator();
        while (listIterator.hasNext()) {
            String str = (String) listIterator.next();
            if ("--".equals(str)) {
                i = 1;
            } else if ("-".equals(str)) {
                if (z) {
                    i = 1;
                } else {
                    this.a.addArg(str);
                }
            } else if (!str.startsWith("-")) {
                this.a.addArg(str);
                if (z) {
                    i = 1;
                }
            } else if (!z || a().hasOption(str)) {
                a(str, listIterator);
            } else {
                this.a.addArg(str);
                i = 1;
            }
            if (i != 0) {
                while (listIterator.hasNext()) {
                    str = (String) listIterator.next();
                    if (!"--".equals(str)) {
                        this.a.addArg(str);
                    }
                }
            }
        }
        a(properties);
        c();
        return this.a;
    }

    protected void a(Properties properties) {
        if (properties != null) {
            Enumeration propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                String obj = propertyNames.nextElement().toString();
                if (!this.a.hasOption(obj)) {
                    Option option = a().getOption(obj);
                    obj = properties.getProperty(obj);
                    if (option.hasArg()) {
                        if (option.getValues() == null || option.getValues().length == 0) {
                            try {
                                option.addValueForProcessing(obj);
                            } catch (RuntimeException e) {
                            }
                        }
                    } else if (!("yes".equalsIgnoreCase(obj) || "true".equalsIgnoreCase(obj) || "1".equalsIgnoreCase(obj))) {
                        return;
                    }
                    this.a.addOption(option);
                }
            }
        }
    }

    protected void c() throws MissingOptionException {
        if (!b().isEmpty()) {
            throw new MissingOptionException(b());
        }
    }

    public void a(Option option, ListIterator listIterator) throws ParseException {
        while (listIterator.hasNext()) {
            String str = (String) listIterator.next();
            if (a().hasOption(str) && str.startsWith("-")) {
                listIterator.previous();
                break;
            } else {
                try {
                    option.addValueForProcessing(h.b(str));
                } catch (RuntimeException e) {
                    listIterator.previous();
                }
            }
        }
        if (option.getValues() == null && !option.hasOptionalArg()) {
            throw new MissingArgumentException(option);
        }
    }

    protected void a(String str, ListIterator listIterator) throws ParseException {
        if (a().hasOption(str)) {
            Option option = (Option) a().getOption(str).clone();
            if (option.isRequired()) {
                b().remove(option.getKey());
            }
            if (a().getOptionGroup(option) != null) {
                OptionGroup optionGroup = a().getOptionGroup(option);
                if (optionGroup.isRequired()) {
                    b().remove(optionGroup);
                }
                optionGroup.setSelected(option);
            }
            if (option.hasArg()) {
                a(option, listIterator);
            }
            this.a.addOption(option);
            return;
        }
        throw new UnrecognizedOptionException(new StringBuffer().append("Unrecognized option: ").append(str).toString(), str);
    }
}
