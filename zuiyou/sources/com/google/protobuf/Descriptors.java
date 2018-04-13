package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.DescriptorProto.ExtensionRange;
import com.google.protobuf.DescriptorProtos.DescriptorProto.ReservedRange;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Label;
import com.google.protobuf.FieldSet.FieldDescriptorLite;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.MessageLite.Builder;
import com.google.protobuf.WireFormat.FieldType;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Logger;

public final class Descriptors {
    private static final Logger logger = Logger.getLogger(Descriptors.class.getName());

    public static abstract class GenericDescriptor {
        public abstract FileDescriptor getFile();

        public abstract String getFullName();

        public abstract String getName();

        public abstract Message toProto();
    }

    public static final class Descriptor extends GenericDescriptor {
        private final Descriptor containingType;
        private final EnumDescriptor[] enumTypes;
        private final FieldDescriptor[] extensions;
        private final FieldDescriptor[] fields;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private final Descriptor[] nestedTypes;
        private final OneofDescriptor[] oneofs;
        private DescriptorProtos$DescriptorProto proto;

        public int getIndex() {
            return this.index;
        }

        public DescriptorProtos$DescriptorProto toProto() {
            return this.proto;
        }

        public String getName() {
            return this.proto.getName();
        }

        public String getFullName() {
            return this.fullName;
        }

        public FileDescriptor getFile() {
            return this.file;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public DescriptorProtos$MessageOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<FieldDescriptor> getFields() {
            return Collections.unmodifiableList(Arrays.asList(this.fields));
        }

        public List<OneofDescriptor> getOneofs() {
            return Collections.unmodifiableList(Arrays.asList(this.oneofs));
        }

        public List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public List<Descriptor> getNestedTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
        }

        public List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public boolean isExtensionNumber(int i) {
            for (ExtensionRange extensionRange : this.proto.getExtensionRangeList()) {
                if (extensionRange.getStart() <= i && i < extensionRange.getEnd()) {
                    return true;
                }
            }
            return false;
        }

        public boolean isReservedNumber(int i) {
            for (ReservedRange reservedRange : this.proto.getReservedRangeList()) {
                if (reservedRange.getStart() <= i && i < reservedRange.getEnd()) {
                    return true;
                }
            }
            return false;
        }

        public boolean isReservedName(String str) {
            Internal.checkNotNull(str);
            for (String equals : this.proto.getReservedNameList()) {
                if (equals.equals(str)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isExtendable() {
            return this.proto.getExtensionRangeList().size() != 0;
        }

        public FieldDescriptor findFieldByName(String str) {
            GenericDescriptor findSymbol = this.file.pool.findSymbol(this.fullName + '.' + str);
            if (findSymbol == null || !(findSymbol instanceof FieldDescriptor)) {
                return null;
            }
            return (FieldDescriptor) findSymbol;
        }

        public FieldDescriptor findFieldByNumber(int i) {
            return (FieldDescriptor) this.file.pool.fieldsByNumber.get(new DescriptorIntPair(this, i));
        }

        public Descriptor findNestedTypeByName(String str) {
            GenericDescriptor findSymbol = this.file.pool.findSymbol(this.fullName + '.' + str);
            if (findSymbol == null || !(findSymbol instanceof Descriptor)) {
                return null;
            }
            return (Descriptor) findSymbol;
        }

        public EnumDescriptor findEnumTypeByName(String str) {
            GenericDescriptor findSymbol = this.file.pool.findSymbol(this.fullName + '.' + str);
            if (findSymbol == null || !(findSymbol instanceof EnumDescriptor)) {
                return null;
            }
            return (EnumDescriptor) findSymbol;
        }

        Descriptor(String str) throws DescriptorValidationException {
            String substring;
            String str2 = "";
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf != -1) {
                substring = str.substring(lastIndexOf + 1);
                str2 = str.substring(0, lastIndexOf);
            } else {
                substring = str;
            }
            this.index = 0;
            this.proto = DescriptorProtos$DescriptorProto.newBuilder().setName(substring).addExtensionRange(ExtensionRange.newBuilder().setStart(1).setEnd(SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING).build()).build();
            this.fullName = str;
            this.containingType = null;
            this.nestedTypes = new Descriptor[0];
            this.enumTypes = new EnumDescriptor[0];
            this.fields = new FieldDescriptor[0];
            this.extensions = new FieldDescriptor[0];
            this.oneofs = new OneofDescriptor[0];
            this.file = new FileDescriptor(str2, this);
        }

        private Descriptor(DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i) throws DescriptorValidationException {
            int i2;
            int i3;
            this.index = i;
            this.proto = descriptorProtos$DescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, descriptorProtos$DescriptorProto.getName());
            this.file = fileDescriptor;
            this.containingType = descriptor;
            this.oneofs = new OneofDescriptor[descriptorProtos$DescriptorProto.getOneofDeclCount()];
            for (i2 = 0; i2 < descriptorProtos$DescriptorProto.getOneofDeclCount(); i2++) {
                this.oneofs[i2] = new OneofDescriptor(descriptorProtos$DescriptorProto.getOneofDecl(i2), fileDescriptor, this, i2);
            }
            this.nestedTypes = new Descriptor[descriptorProtos$DescriptorProto.getNestedTypeCount()];
            for (i3 = 0; i3 < descriptorProtos$DescriptorProto.getNestedTypeCount(); i3++) {
                this.nestedTypes[i3] = new Descriptor(descriptorProtos$DescriptorProto.getNestedType(i3), fileDescriptor, this, i3);
            }
            this.enumTypes = new EnumDescriptor[descriptorProtos$DescriptorProto.getEnumTypeCount()];
            for (i2 = 0; i2 < descriptorProtos$DescriptorProto.getEnumTypeCount(); i2++) {
                this.enumTypes[i2] = new EnumDescriptor(descriptorProtos$DescriptorProto.getEnumType(i2), fileDescriptor, this, i2);
            }
            this.fields = new FieldDescriptor[descriptorProtos$DescriptorProto.getFieldCount()];
            for (i2 = 0; i2 < descriptorProtos$DescriptorProto.getFieldCount(); i2++) {
                this.fields[i2] = new FieldDescriptor(descriptorProtos$DescriptorProto.getField(i2), fileDescriptor, this, i2, false);
            }
            this.extensions = new FieldDescriptor[descriptorProtos$DescriptorProto.getExtensionCount()];
            for (i2 = 0; i2 < descriptorProtos$DescriptorProto.getExtensionCount(); i2++) {
                this.extensions[i2] = new FieldDescriptor(descriptorProtos$DescriptorProto.getExtension(i2), fileDescriptor, this, i2, true);
            }
            for (i3 = 0; i3 < descriptorProtos$DescriptorProto.getOneofDeclCount(); i3++) {
                this.oneofs[i3].fields = new FieldDescriptor[this.oneofs[i3].getFieldCount()];
                this.oneofs[i3].fieldCount = 0;
            }
            for (i3 = 0; i3 < descriptorProtos$DescriptorProto.getFieldCount(); i3++) {
                OneofDescriptor containingOneof = this.fields[i3].getContainingOneof();
                if (containingOneof != null) {
                    containingOneof.fields[containingOneof.fieldCount = containingOneof.fieldCount + 1] = this.fields[i3];
                }
            }
            fileDescriptor.pool.addSymbol(this);
        }

        private void crossLink() throws DescriptorValidationException {
            int i = 0;
            for (Descriptor crossLink : this.nestedTypes) {
                crossLink.crossLink();
            }
            for (FieldDescriptor access$900 : this.fields) {
                access$900.crossLink();
            }
            FieldDescriptor[] fieldDescriptorArr = this.extensions;
            int length = fieldDescriptorArr.length;
            while (i < length) {
                fieldDescriptorArr[i].crossLink();
                i++;
            }
        }

        private void setProto(DescriptorProtos$DescriptorProto descriptorProtos$DescriptorProto) {
            int i;
            int i2 = 0;
            this.proto = descriptorProtos$DescriptorProto;
            for (i = 0; i < this.nestedTypes.length; i++) {
                this.nestedTypes[i].setProto(descriptorProtos$DescriptorProto.getNestedType(i));
            }
            for (i = 0; i < this.oneofs.length; i++) {
                this.oneofs[i].setProto(descriptorProtos$DescriptorProto.getOneofDecl(i));
            }
            for (i = 0; i < this.enumTypes.length; i++) {
                this.enumTypes[i].setProto(descriptorProtos$DescriptorProto.getEnumType(i));
            }
            for (i = 0; i < this.fields.length; i++) {
                this.fields[i].setProto(descriptorProtos$DescriptorProto.getField(i));
            }
            while (i2 < this.extensions.length) {
                this.extensions[i2].setProto(descriptorProtos$DescriptorProto.getExtension(i2));
                i2++;
            }
        }
    }

    private static final class DescriptorPool {
        private boolean allowUnknownDependencies;
        private final Set<FileDescriptor> dependencies = new HashSet();
        private final Map<String, GenericDescriptor> descriptorsByName = new HashMap();
        private final Map<DescriptorIntPair, EnumValueDescriptor> enumValuesByNumber = new HashMap();
        private final Map<DescriptorIntPair, FieldDescriptor> fieldsByNumber = new HashMap();

        private static final class DescriptorIntPair {
            private final GenericDescriptor descriptor;
            private final int number;

            DescriptorIntPair(GenericDescriptor genericDescriptor, int i) {
                this.descriptor = genericDescriptor;
                this.number = i;
            }

            public int hashCode() {
                return (this.descriptor.hashCode() * 65535) + this.number;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof DescriptorIntPair)) {
                    return false;
                }
                DescriptorIntPair descriptorIntPair = (DescriptorIntPair) obj;
                if (this.descriptor == descriptorIntPair.descriptor && this.number == descriptorIntPair.number) {
                    return true;
                }
                return false;
            }
        }

        private static final class PackageDescriptor extends GenericDescriptor {
            private final FileDescriptor file;
            private final String fullName;
            private final String name;

            public Message toProto() {
                return this.file.toProto();
            }

            public String getName() {
                return this.name;
            }

            public String getFullName() {
                return this.fullName;
            }

            public FileDescriptor getFile() {
                return this.file;
            }

            PackageDescriptor(String str, String str2, FileDescriptor fileDescriptor) {
                this.file = fileDescriptor;
                this.fullName = str2;
                this.name = str;
            }
        }

        enum SearchFilter {
            TYPES_ONLY,
            AGGREGATES_ONLY,
            ALL_SYMBOLS
        }

        DescriptorPool(FileDescriptor[] fileDescriptorArr, boolean z) {
            this.allowUnknownDependencies = z;
            for (int i = 0; i < fileDescriptorArr.length; i++) {
                this.dependencies.add(fileDescriptorArr[i]);
                importPublicDependencies(fileDescriptorArr[i]);
            }
            for (FileDescriptor fileDescriptor : this.dependencies) {
                try {
                    addPackage(fileDescriptor.getPackage(), fileDescriptor);
                } catch (DescriptorValidationException e) {
                    throw new AssertionError(e);
                }
            }
        }

        private void importPublicDependencies(FileDescriptor fileDescriptor) {
            for (FileDescriptor fileDescriptor2 : fileDescriptor.getPublicDependencies()) {
                if (this.dependencies.add(fileDescriptor2)) {
                    importPublicDependencies(fileDescriptor2);
                }
            }
        }

        GenericDescriptor findSymbol(String str) {
            return findSymbol(str, SearchFilter.ALL_SYMBOLS);
        }

        GenericDescriptor findSymbol(String str, SearchFilter searchFilter) {
            GenericDescriptor genericDescriptor = (GenericDescriptor) this.descriptorsByName.get(str);
            if (genericDescriptor != null) {
                if (searchFilter == SearchFilter.ALL_SYMBOLS) {
                    return genericDescriptor;
                }
                if (searchFilter == SearchFilter.TYPES_ONLY && isType(genericDescriptor)) {
                    return genericDescriptor;
                }
                if (searchFilter == SearchFilter.AGGREGATES_ONLY && isAggregate(genericDescriptor)) {
                    return genericDescriptor;
                }
            }
            for (FileDescriptor access$1400 : this.dependencies) {
                genericDescriptor = (GenericDescriptor) access$1400.pool.descriptorsByName.get(str);
                if (genericDescriptor != null) {
                    if (searchFilter == SearchFilter.ALL_SYMBOLS) {
                        return genericDescriptor;
                    }
                    if (searchFilter == SearchFilter.TYPES_ONLY && isType(genericDescriptor)) {
                        return genericDescriptor;
                    }
                    if (searchFilter == SearchFilter.AGGREGATES_ONLY && isAggregate(genericDescriptor)) {
                        return genericDescriptor;
                    }
                }
            }
            return null;
        }

        boolean isType(GenericDescriptor genericDescriptor) {
            return (genericDescriptor instanceof Descriptor) || (genericDescriptor instanceof EnumDescriptor);
        }

        boolean isAggregate(GenericDescriptor genericDescriptor) {
            return (genericDescriptor instanceof Descriptor) || (genericDescriptor instanceof EnumDescriptor) || (genericDescriptor instanceof PackageDescriptor) || (genericDescriptor instanceof ServiceDescriptor);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        com.google.protobuf.Descriptors.GenericDescriptor lookupSymbol(java.lang.String r9, com.google.protobuf.Descriptors.GenericDescriptor r10, com.google.protobuf.Descriptors.DescriptorPool.SearchFilter r11) throws com.google.protobuf.Descriptors.DescriptorValidationException {
            /*
            r8 = this;
            r6 = -1;
            r0 = ".";
            r0 = r9.startsWith(r0);
            if (r0 == 0) goto L_0x0051;
        L_0x000a:
            r0 = 1;
            r0 = r9.substring(r0);
            r1 = r8.findSymbol(r0, r11);
            r7 = r0;
            r0 = r1;
            r1 = r7;
        L_0x0016:
            if (r0 != 0) goto L_0x0050;
        L_0x0018:
            r0 = r8.allowUnknownDependencies;
            if (r0 == 0) goto L_0x00a8;
        L_0x001c:
            r0 = com.google.protobuf.Descriptors.DescriptorPool.SearchFilter.TYPES_ONLY;
            if (r11 != r0) goto L_0x00a8;
        L_0x0020:
            r0 = com.google.protobuf.Descriptors.logger;
            r2 = new java.lang.StringBuilder;
            r2.<init>();
            r3 = "The descriptor for message type \"";
            r2 = r2.append(r3);
            r2 = r2.append(r9);
            r3 = "\" can not be found and a placeholder is created for it";
            r2 = r2.append(r3);
            r2 = r2.toString();
            r0.warning(r2);
            r0 = new com.google.protobuf.Descriptors$Descriptor;
            r0.<init>(r1);
            r1 = r8.dependencies;
            r2 = r0.getFile();
            r1.add(r2);
        L_0x0050:
            return r0;
        L_0x0051:
            r0 = 46;
            r2 = r9.indexOf(r0);
            if (r2 != r6) goto L_0x0072;
        L_0x0059:
            r0 = r9;
        L_0x005a:
            r3 = new java.lang.StringBuilder;
            r1 = r10.getFullName();
            r3.<init>(r1);
        L_0x0063:
            r1 = ".";
            r4 = r3.lastIndexOf(r1);
            if (r4 != r6) goto L_0x0078;
        L_0x006c:
            r0 = r8.findSymbol(r9, r11);
            r1 = r9;
            goto L_0x0016;
        L_0x0072:
            r0 = 0;
            r0 = r9.substring(r0, r2);
            goto L_0x005a;
        L_0x0078:
            r1 = r4 + 1;
            r3.setLength(r1);
            r3.append(r0);
            r1 = r3.toString();
            r5 = com.google.protobuf.Descriptors.DescriptorPool.SearchFilter.AGGREGATES_ONLY;
            r1 = r8.findSymbol(r1, r5);
            if (r1 == 0) goto L_0x00a4;
        L_0x008c:
            if (r2 == r6) goto L_0x00c9;
        L_0x008e:
            r0 = r4 + 1;
            r3.setLength(r0);
            r3.append(r9);
            r0 = r3.toString();
            r0 = r8.findSymbol(r0, r11);
        L_0x009e:
            r1 = r3.toString();
            goto L_0x0016;
        L_0x00a4:
            r3.setLength(r4);
            goto L_0x0063;
        L_0x00a8:
            r0 = new com.google.protobuf.Descriptors$DescriptorValidationException;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = 34;
            r1 = r1.append(r2);
            r1 = r1.append(r9);
            r2 = "\" is not defined.";
            r1 = r1.append(r2);
            r1 = r1.toString();
            r2 = 0;
            r0.<init>(r10, r1);
            throw r0;
        L_0x00c9:
            r0 = r1;
            goto L_0x009e;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Descriptors.DescriptorPool.lookupSymbol(java.lang.String, com.google.protobuf.Descriptors$GenericDescriptor, com.google.protobuf.Descriptors$DescriptorPool$SearchFilter):com.google.protobuf.Descriptors$GenericDescriptor");
        }

        void addSymbol(GenericDescriptor genericDescriptor) throws DescriptorValidationException {
            validateSymbolName(genericDescriptor);
            String fullName = genericDescriptor.getFullName();
            int lastIndexOf = fullName.lastIndexOf(46);
            GenericDescriptor genericDescriptor2 = (GenericDescriptor) this.descriptorsByName.put(fullName, genericDescriptor);
            if (genericDescriptor2 != null) {
                this.descriptorsByName.put(fullName, genericDescriptor2);
                if (genericDescriptor.getFile() != genericDescriptor2.getFile()) {
                    throw new DescriptorValidationException(genericDescriptor, '\"' + fullName + "\" is already defined in file \"" + genericDescriptor2.getFile().getName() + "\".");
                } else if (lastIndexOf == -1) {
                    throw new DescriptorValidationException(genericDescriptor, '\"' + fullName + "\" is already defined.");
                } else {
                    throw new DescriptorValidationException(genericDescriptor, '\"' + fullName.substring(lastIndexOf + 1) + "\" is already defined in \"" + fullName.substring(0, lastIndexOf) + "\".");
                }
            }
        }

        void addPackage(String str, FileDescriptor fileDescriptor) throws DescriptorValidationException {
            String str2;
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf == -1) {
                str2 = str;
            } else {
                addPackage(str.substring(0, lastIndexOf), fileDescriptor);
                str2 = str.substring(lastIndexOf + 1);
            }
            GenericDescriptor genericDescriptor = (GenericDescriptor) this.descriptorsByName.put(str, new PackageDescriptor(str2, str, fileDescriptor));
            if (genericDescriptor != null) {
                this.descriptorsByName.put(str, genericDescriptor);
                if (!(genericDescriptor instanceof PackageDescriptor)) {
                    throw new DescriptorValidationException(fileDescriptor, '\"' + str2 + "\" is already defined (as something other than a package) in file \"" + genericDescriptor.getFile().getName() + "\".");
                }
            }
        }

        void addFieldByNumber(FieldDescriptor fieldDescriptor) throws DescriptorValidationException {
            DescriptorIntPair descriptorIntPair = new DescriptorIntPair(fieldDescriptor.getContainingType(), fieldDescriptor.getNumber());
            FieldDescriptor fieldDescriptor2 = (FieldDescriptor) this.fieldsByNumber.put(descriptorIntPair, fieldDescriptor);
            if (fieldDescriptor2 != null) {
                this.fieldsByNumber.put(descriptorIntPair, fieldDescriptor2);
                throw new DescriptorValidationException((GenericDescriptor) fieldDescriptor, "Field number " + fieldDescriptor.getNumber() + " has already been used in \"" + fieldDescriptor.getContainingType().getFullName() + "\" by field \"" + fieldDescriptor2.getName() + "\".");
            }
        }

        void addEnumValueByNumber(EnumValueDescriptor enumValueDescriptor) {
            DescriptorIntPair descriptorIntPair = new DescriptorIntPair(enumValueDescriptor.getType(), enumValueDescriptor.getNumber());
            EnumValueDescriptor enumValueDescriptor2 = (EnumValueDescriptor) this.enumValuesByNumber.put(descriptorIntPair, enumValueDescriptor);
            if (enumValueDescriptor2 != null) {
                this.enumValuesByNumber.put(descriptorIntPair, enumValueDescriptor2);
            }
        }

        static void validateSymbolName(GenericDescriptor genericDescriptor) throws DescriptorValidationException {
            String name = genericDescriptor.getName();
            if (name.length() == 0) {
                throw new DescriptorValidationException(genericDescriptor, "Missing name.");
            }
            Object obj = 1;
            int i = 0;
            while (i < name.length()) {
                char charAt = name.charAt(i);
                if (charAt >= '') {
                    obj = null;
                }
                if (!(Character.isLetter(charAt) || charAt == '_' || (Character.isDigit(charAt) && i > 0))) {
                    obj = null;
                }
                i++;
            }
            if (obj == null) {
                throw new DescriptorValidationException(genericDescriptor, '\"' + name + "\" is not a valid identifier.");
            }
        }
    }

    public static class DescriptorValidationException extends Exception {
        private static final long serialVersionUID = 5750205775490483148L;
        private final String description;
        private final String name;
        private final Message proto;

        public String getProblemSymbolName() {
            return this.name;
        }

        public Message getProblemProto() {
            return this.proto;
        }

        public String getDescription() {
            return this.description;
        }

        private DescriptorValidationException(GenericDescriptor genericDescriptor, String str) {
            super(genericDescriptor.getFullName() + ": " + str);
            this.name = genericDescriptor.getFullName();
            this.proto = genericDescriptor.toProto();
            this.description = str;
        }

        private DescriptorValidationException(GenericDescriptor genericDescriptor, String str, Throwable th) {
            this(genericDescriptor, str);
            initCause(th);
        }

        private DescriptorValidationException(FileDescriptor fileDescriptor, String str) {
            super(fileDescriptor.getName() + ": " + str);
            this.name = fileDescriptor.getName();
            this.proto = fileDescriptor.toProto();
            this.description = str;
        }
    }

    public static final class EnumDescriptor extends GenericDescriptor implements EnumLiteMap<EnumValueDescriptor> {
        private final Descriptor containingType;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos$EnumDescriptorProto proto;
        private final WeakHashMap<Integer, WeakReference<EnumValueDescriptor>> unknownValues;
        private EnumValueDescriptor[] values;

        public int getIndex() {
            return this.index;
        }

        public DescriptorProtos$EnumDescriptorProto toProto() {
            return this.proto;
        }

        public String getName() {
            return this.proto.getName();
        }

        public String getFullName() {
            return this.fullName;
        }

        public FileDescriptor getFile() {
            return this.file;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public DescriptorProtos$EnumOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<EnumValueDescriptor> getValues() {
            return Collections.unmodifiableList(Arrays.asList(this.values));
        }

        public EnumValueDescriptor findValueByName(String str) {
            GenericDescriptor findSymbol = this.file.pool.findSymbol(this.fullName + '.' + str);
            if (findSymbol == null || !(findSymbol instanceof EnumValueDescriptor)) {
                return null;
            }
            return (EnumValueDescriptor) findSymbol;
        }

        public EnumValueDescriptor findValueByNumber(int i) {
            return (EnumValueDescriptor) this.file.pool.enumValuesByNumber.get(new DescriptorIntPair(this, i));
        }

        public EnumValueDescriptor findValueByNumberCreatingIfUnknown(int i) {
            EnumValueDescriptor findValueByNumber = findValueByNumber(i);
            if (findValueByNumber != null) {
                return findValueByNumber;
            }
            EnumValueDescriptor enumValueDescriptor;
            synchronized (this) {
                Integer num = new Integer(i);
                WeakReference weakReference = (WeakReference) this.unknownValues.get(num);
                if (weakReference != null) {
                    enumValueDescriptor = (EnumValueDescriptor) weakReference.get();
                } else {
                    enumValueDescriptor = findValueByNumber;
                }
                if (enumValueDescriptor == null) {
                    enumValueDescriptor = new EnumValueDescriptor(this.file, this, num);
                    this.unknownValues.put(num, new WeakReference(enumValueDescriptor));
                }
            }
            return enumValueDescriptor;
        }

        int getUnknownEnumValueDescriptorCount() {
            return this.unknownValues.size();
        }

        private EnumDescriptor(DescriptorProtos$EnumDescriptorProto descriptorProtos$EnumDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i) throws DescriptorValidationException {
            this.unknownValues = new WeakHashMap();
            this.index = i;
            this.proto = descriptorProtos$EnumDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, descriptorProtos$EnumDescriptorProto.getName());
            this.file = fileDescriptor;
            this.containingType = descriptor;
            if (descriptorProtos$EnumDescriptorProto.getValueCount() == 0) {
                throw new DescriptorValidationException((GenericDescriptor) this, "Enums must contain at least one value.");
            }
            this.values = new EnumValueDescriptor[descriptorProtos$EnumDescriptorProto.getValueCount()];
            for (int i2 = 0; i2 < descriptorProtos$EnumDescriptorProto.getValueCount(); i2++) {
                this.values[i2] = new EnumValueDescriptor(descriptorProtos$EnumDescriptorProto.getValue(i2), fileDescriptor, this, i2);
            }
            fileDescriptor.pool.addSymbol(this);
        }

        private void setProto(DescriptorProtos$EnumDescriptorProto descriptorProtos$EnumDescriptorProto) {
            this.proto = descriptorProtos$EnumDescriptorProto;
            for (int i = 0; i < this.values.length; i++) {
                this.values[i].setProto(descriptorProtos$EnumDescriptorProto.getValue(i));
            }
        }
    }

    public static final class EnumValueDescriptor extends GenericDescriptor implements EnumLite {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private Integer number;
        private DescriptorProtos$EnumValueDescriptorProto proto;
        private final EnumDescriptor type;

        public int getIndex() {
            return this.index;
        }

        public DescriptorProtos$EnumValueDescriptorProto toProto() {
            return this.proto;
        }

        public String getName() {
            return this.proto.getName();
        }

        public int getNumber() {
            return this.proto.getNumber();
        }

        public String toString() {
            return this.proto.getName();
        }

        public String getFullName() {
            return this.fullName;
        }

        public FileDescriptor getFile() {
            return this.file;
        }

        public EnumDescriptor getType() {
            return this.type;
        }

        public DescriptorProtos$EnumValueOptions getOptions() {
            return this.proto.getOptions();
        }

        private EnumValueDescriptor(DescriptorProtos$EnumValueDescriptorProto descriptorProtos$EnumValueDescriptorProto, FileDescriptor fileDescriptor, EnumDescriptor enumDescriptor, int i) throws DescriptorValidationException {
            this.index = i;
            this.proto = descriptorProtos$EnumValueDescriptorProto;
            this.file = fileDescriptor;
            this.type = enumDescriptor;
            this.fullName = enumDescriptor.getFullName() + '.' + descriptorProtos$EnumValueDescriptorProto.getName();
            fileDescriptor.pool.addSymbol(this);
            fileDescriptor.pool.addEnumValueByNumber(this);
        }

        private EnumValueDescriptor(FileDescriptor fileDescriptor, EnumDescriptor enumDescriptor, Integer num) {
            DescriptorProtos$EnumValueDescriptorProto build = DescriptorProtos$EnumValueDescriptorProto.newBuilder().setName("UNKNOWN_ENUM_VALUE_" + enumDescriptor.getName() + "_" + num).setNumber(num.intValue()).build();
            this.index = -1;
            this.proto = build;
            this.file = fileDescriptor;
            this.type = enumDescriptor;
            this.fullName = enumDescriptor.getFullName() + '.' + build.getName();
            this.number = num;
        }

        private void setProto(DescriptorProtos$EnumValueDescriptorProto descriptorProtos$EnumValueDescriptorProto) {
            this.proto = descriptorProtos$EnumValueDescriptorProto;
        }
    }

    public static final class FieldDescriptor extends GenericDescriptor implements FieldDescriptorLite<FieldDescriptor>, Comparable<FieldDescriptor> {
        private static final FieldType[] table = FieldType.values();
        private OneofDescriptor containingOneof;
        private Descriptor containingType;
        private Object defaultValue;
        private EnumDescriptor enumType;
        private final Descriptor extensionScope;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private final String jsonName;
        private Descriptor messageType;
        private DescriptorProtos$FieldDescriptorProto proto;
        private Type type;

        public enum JavaType {
            INT(Integer.valueOf(0)),
            LONG(Long.valueOf(0)),
            FLOAT(Float.valueOf(0.0f)),
            DOUBLE(Double.valueOf(0.0d)),
            BOOLEAN(Boolean.valueOf(false)),
            STRING(""),
            BYTE_STRING(ByteString.EMPTY),
            ENUM(null),
            MESSAGE(null);
            
            private final Object defaultDefault;

            private JavaType(Object obj) {
                this.defaultDefault = obj;
            }
        }

        public enum Type {
            DOUBLE(JavaType.DOUBLE),
            FLOAT(JavaType.FLOAT),
            INT64(JavaType.LONG),
            UINT64(JavaType.LONG),
            INT32(JavaType.INT),
            FIXED64(JavaType.LONG),
            FIXED32(JavaType.INT),
            BOOL(JavaType.BOOLEAN),
            STRING(JavaType.STRING),
            GROUP(JavaType.MESSAGE),
            MESSAGE(JavaType.MESSAGE),
            BYTES(JavaType.BYTE_STRING),
            UINT32(JavaType.INT),
            ENUM(JavaType.ENUM),
            SFIXED32(JavaType.INT),
            SFIXED64(JavaType.LONG),
            SINT32(JavaType.INT),
            SINT64(JavaType.LONG);
            
            private JavaType javaType;

            private Type(JavaType javaType) {
                this.javaType = javaType;
            }

            public com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type toProto() {
                return com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type.forNumber(ordinal() + 1);
            }

            public JavaType getJavaType() {
                return this.javaType;
            }

            public static Type valueOf(com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type type) {
                return values()[type.getNumber() - 1];
            }
        }

        public int getIndex() {
            return this.index;
        }

        public DescriptorProtos$FieldDescriptorProto toProto() {
            return this.proto;
        }

        public String getName() {
            return this.proto.getName();
        }

        public int getNumber() {
            return this.proto.getNumber();
        }

        public String getFullName() {
            return this.fullName;
        }

        public String getJsonName() {
            return this.jsonName;
        }

        public JavaType getJavaType() {
            return this.type.getJavaType();
        }

        public com.google.protobuf.WireFormat.JavaType getLiteJavaType() {
            return getLiteType().getJavaType();
        }

        public FileDescriptor getFile() {
            return this.file;
        }

        public Type getType() {
            return this.type;
        }

        public FieldType getLiteType() {
            return table[this.type.ordinal()];
        }

        public boolean needsUtf8Check() {
            if (this.type != Type.STRING) {
                return false;
            }
            if (getContainingType().getOptions().getMapEntry() || getFile().getSyntax() == Syntax.PROTO3) {
                return true;
            }
            return getFile().getOptions().getJavaStringCheckUtf8();
        }

        public boolean isMapField() {
            return getType() == Type.MESSAGE && isRepeated() && getMessageType().getOptions().getMapEntry();
        }

        static {
            if (Type.values().length != com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type.values().length) {
                throw new RuntimeException("descriptor.proto has a new declared type but Descriptors.java wasn't updated.");
            }
        }

        public boolean isRequired() {
            return this.proto.getLabel() == Label.LABEL_REQUIRED;
        }

        public boolean isOptional() {
            return this.proto.getLabel() == Label.LABEL_OPTIONAL;
        }

        public boolean isRepeated() {
            return this.proto.getLabel() == Label.LABEL_REPEATED;
        }

        public boolean isPacked() {
            if (!isPackable()) {
                return false;
            }
            if (getFile().getSyntax() == Syntax.PROTO2) {
                return getOptions().getPacked();
            }
            if (!getOptions().hasPacked() || getOptions().getPacked()) {
                return true;
            }
            return false;
        }

        public boolean isPackable() {
            return isRepeated() && getLiteType().isPackable();
        }

        public boolean hasDefaultValue() {
            return this.proto.hasDefaultValue();
        }

        public Object getDefaultValue() {
            if (getJavaType() != JavaType.MESSAGE) {
                return this.defaultValue;
            }
            throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
        }

        public DescriptorProtos$FieldOptions getOptions() {
            return this.proto.getOptions();
        }

        public boolean isExtension() {
            return this.proto.hasExtendee();
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public OneofDescriptor getContainingOneof() {
            return this.containingOneof;
        }

        public Descriptor getExtensionScope() {
            if (isExtension()) {
                return this.extensionScope;
            }
            throw new UnsupportedOperationException("This field is not an extension.");
        }

        public Descriptor getMessageType() {
            if (getJavaType() == JavaType.MESSAGE) {
                return this.messageType;
            }
            throw new UnsupportedOperationException("This field is not of message type.");
        }

        public EnumDescriptor getEnumType() {
            if (getJavaType() == JavaType.ENUM) {
                return this.enumType;
            }
            throw new UnsupportedOperationException("This field is not of enum type.");
        }

        public int compareTo(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.containingType == this.containingType) {
                return getNumber() - fieldDescriptor.getNumber();
            }
            throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
        }

        public String toString() {
            return getFullName();
        }

        private static String fieldNameToJsonName(String str) {
            StringBuilder stringBuilder = new StringBuilder(str.length());
            Object obj = null;
            for (int i = 0; i < str.length(); i++) {
                Character valueOf = Character.valueOf(str.charAt(i));
                if (valueOf.charValue() == '_') {
                    obj = 1;
                } else if (obj != null) {
                    stringBuilder.append(Character.toUpperCase(valueOf.charValue()));
                    obj = null;
                } else {
                    stringBuilder.append(valueOf);
                }
            }
            return stringBuilder.toString();
        }

        private FieldDescriptor(DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, boolean z) throws DescriptorValidationException {
            this.index = i;
            this.proto = descriptorProtos$FieldDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, descriptorProtos$FieldDescriptorProto.getName());
            this.file = fileDescriptor;
            if (descriptorProtos$FieldDescriptorProto.hasJsonName()) {
                this.jsonName = descriptorProtos$FieldDescriptorProto.getJsonName();
            } else {
                this.jsonName = fieldNameToJsonName(descriptorProtos$FieldDescriptorProto.getName());
            }
            if (descriptorProtos$FieldDescriptorProto.hasType()) {
                this.type = Type.valueOf(descriptorProtos$FieldDescriptorProto.getType());
            }
            if (getNumber() <= 0) {
                throw new DescriptorValidationException((GenericDescriptor) this, "Field numbers must be positive integers.");
            }
            if (z) {
                if (descriptorProtos$FieldDescriptorProto.hasExtendee()) {
                    this.containingType = null;
                    if (descriptor != null) {
                        this.extensionScope = descriptor;
                    } else {
                        this.extensionScope = null;
                    }
                    if (descriptorProtos$FieldDescriptorProto.hasOneofIndex()) {
                        throw new DescriptorValidationException((GenericDescriptor) this, "FieldDescriptorProto.oneof_index set for extension field.");
                    }
                    this.containingOneof = null;
                } else {
                    throw new DescriptorValidationException((GenericDescriptor) this, "FieldDescriptorProto.extendee not set for extension field.");
                }
            } else if (descriptorProtos$FieldDescriptorProto.hasExtendee()) {
                throw new DescriptorValidationException((GenericDescriptor) this, "FieldDescriptorProto.extendee set for non-extension field.");
            } else {
                this.containingType = descriptor;
                if (!descriptorProtos$FieldDescriptorProto.hasOneofIndex()) {
                    this.containingOneof = null;
                } else if (descriptorProtos$FieldDescriptorProto.getOneofIndex() < 0 || descriptorProtos$FieldDescriptorProto.getOneofIndex() >= descriptor.toProto().getOneofDeclCount()) {
                    throw new DescriptorValidationException((GenericDescriptor) this, "FieldDescriptorProto.oneof_index is out of range for type " + descriptor.getName());
                } else {
                    this.containingOneof = (OneofDescriptor) descriptor.getOneofs().get(descriptorProtos$FieldDescriptorProto.getOneofIndex());
                    this.containingOneof.fieldCount = this.containingOneof.fieldCount + 1;
                }
                this.extensionScope = null;
            }
            fileDescriptor.pool.addSymbol(this);
        }

        private void crossLink() throws DescriptorValidationException {
            GenericDescriptor lookupSymbol;
            if (this.proto.hasExtendee()) {
                lookupSymbol = this.file.pool.lookupSymbol(this.proto.getExtendee(), this, SearchFilter.TYPES_ONLY);
                if (lookupSymbol instanceof Descriptor) {
                    this.containingType = (Descriptor) lookupSymbol;
                    if (!getContainingType().isExtensionNumber(getNumber())) {
                        throw new DescriptorValidationException((GenericDescriptor) this, '\"' + getContainingType().getFullName() + "\" does not declare " + getNumber() + " as an extension number.");
                    }
                }
                throw new DescriptorValidationException((GenericDescriptor) this, '\"' + this.proto.getExtendee() + "\" is not a message type.");
            }
            if (this.proto.hasTypeName()) {
                lookupSymbol = this.file.pool.lookupSymbol(this.proto.getTypeName(), this, SearchFilter.TYPES_ONLY);
                if (!this.proto.hasType()) {
                    if (lookupSymbol instanceof Descriptor) {
                        this.type = Type.MESSAGE;
                    } else if (lookupSymbol instanceof EnumDescriptor) {
                        this.type = Type.ENUM;
                    } else {
                        throw new DescriptorValidationException((GenericDescriptor) this, '\"' + this.proto.getTypeName() + "\" is not a type.");
                    }
                }
                if (getJavaType() == JavaType.MESSAGE) {
                    if (lookupSymbol instanceof Descriptor) {
                        this.messageType = (Descriptor) lookupSymbol;
                        if (this.proto.hasDefaultValue()) {
                            throw new DescriptorValidationException((GenericDescriptor) this, "Messages can't have default values.");
                        }
                    }
                    throw new DescriptorValidationException((GenericDescriptor) this, '\"' + this.proto.getTypeName() + "\" is not a message type.");
                } else if (getJavaType() != JavaType.ENUM) {
                    throw new DescriptorValidationException((GenericDescriptor) this, "Field with primitive type has type_name.");
                } else if (lookupSymbol instanceof EnumDescriptor) {
                    this.enumType = (EnumDescriptor) lookupSymbol;
                } else {
                    throw new DescriptorValidationException((GenericDescriptor) this, '\"' + this.proto.getTypeName() + "\" is not an enum type.");
                }
            } else if (getJavaType() == JavaType.MESSAGE || getJavaType() == JavaType.ENUM) {
                throw new DescriptorValidationException((GenericDescriptor) this, "Field with message or enum type missing type_name.");
            }
            if (!this.proto.getOptions().getPacked() || isPackable()) {
                if (!this.proto.hasDefaultValue()) {
                    if (!isRepeated()) {
                        switch (getJavaType()) {
                            case ENUM:
                                this.defaultValue = this.enumType.getValues().get(0);
                                break;
                            case MESSAGE:
                                this.defaultValue = null;
                                break;
                            default:
                                this.defaultValue = getJavaType().defaultDefault;
                                break;
                        }
                    }
                    this.defaultValue = Collections.emptyList();
                } else if (isRepeated()) {
                    throw new DescriptorValidationException((GenericDescriptor) this, "Repeated fields cannot have default values.");
                } else {
                    try {
                        switch (getType()) {
                            case INT32:
                            case SINT32:
                            case SFIXED32:
                                this.defaultValue = Integer.valueOf(TextFormat.parseInt32(this.proto.getDefaultValue()));
                                break;
                            case UINT32:
                            case FIXED32:
                                this.defaultValue = Integer.valueOf(TextFormat.parseUInt32(this.proto.getDefaultValue()));
                                break;
                            case INT64:
                            case SINT64:
                            case SFIXED64:
                                this.defaultValue = Long.valueOf(TextFormat.parseInt64(this.proto.getDefaultValue()));
                                break;
                            case UINT64:
                            case FIXED64:
                                this.defaultValue = Long.valueOf(TextFormat.parseUInt64(this.proto.getDefaultValue()));
                                break;
                            case FLOAT:
                                if (!this.proto.getDefaultValue().equals("inf")) {
                                    if (!this.proto.getDefaultValue().equals("-inf")) {
                                        if (!this.proto.getDefaultValue().equals("nan")) {
                                            this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
                                            break;
                                        } else {
                                            this.defaultValue = Float.valueOf(Float.NaN);
                                            break;
                                        }
                                    }
                                    this.defaultValue = Float.valueOf(Float.NEGATIVE_INFINITY);
                                    break;
                                }
                                this.defaultValue = Float.valueOf(Float.POSITIVE_INFINITY);
                                break;
                            case DOUBLE:
                                if (!this.proto.getDefaultValue().equals("inf")) {
                                    if (!this.proto.getDefaultValue().equals("-inf")) {
                                        if (!this.proto.getDefaultValue().equals("nan")) {
                                            this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
                                            break;
                                        } else {
                                            this.defaultValue = Double.valueOf(Double.NaN);
                                            break;
                                        }
                                    }
                                    this.defaultValue = Double.valueOf(Double.NEGATIVE_INFINITY);
                                    break;
                                }
                                this.defaultValue = Double.valueOf(Double.POSITIVE_INFINITY);
                                break;
                            case BOOL:
                                this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
                                break;
                            case STRING:
                                this.defaultValue = this.proto.getDefaultValue();
                                break;
                            case BYTES:
                                this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
                                break;
                            case ENUM:
                                this.defaultValue = this.enumType.findValueByName(this.proto.getDefaultValue());
                                if (this.defaultValue == null) {
                                    throw new DescriptorValidationException((GenericDescriptor) this, "Unknown enum default value: \"" + this.proto.getDefaultValue() + '\"');
                                }
                                break;
                            case MESSAGE:
                            case GROUP:
                                throw new DescriptorValidationException((GenericDescriptor) this, "Message type had default value.");
                        }
                    } catch (Throwable e) {
                        throw new DescriptorValidationException(this, "Couldn't parse default value: " + e.getMessage(), e);
                    } catch (Throwable e2) {
                        throw new DescriptorValidationException(this, "Could not parse default value: \"" + this.proto.getDefaultValue() + '\"', e2);
                    }
                }
                if (!isExtension()) {
                    this.file.pool.addFieldByNumber(this);
                }
                if (this.containingType != null && this.containingType.getOptions().getMessageSetWireFormat()) {
                    if (!isExtension()) {
                        throw new DescriptorValidationException((GenericDescriptor) this, "MessageSets cannot have fields, only extensions.");
                    } else if (!isOptional() || getType() != Type.MESSAGE) {
                        throw new DescriptorValidationException((GenericDescriptor) this, "Extensions of MessageSets must be optional messages.");
                    } else {
                        return;
                    }
                }
                return;
            }
            throw new DescriptorValidationException((GenericDescriptor) this, "[packed = true] can only be specified for repeated primitive fields.");
        }

        private void setProto(DescriptorProtos$FieldDescriptorProto descriptorProtos$FieldDescriptorProto) {
            this.proto = descriptorProtos$FieldDescriptorProto;
        }

        public Builder internalMergeFrom(Builder builder, MessageLite messageLite) {
            return ((Message.Builder) builder).mergeFrom((Message) messageLite);
        }
    }

    public static final class FileDescriptor extends GenericDescriptor {
        private final FileDescriptor[] dependencies;
        private final EnumDescriptor[] enumTypes;
        private final FieldDescriptor[] extensions;
        private final Descriptor[] messageTypes;
        private final DescriptorPool pool;
        private DescriptorProtos$FileDescriptorProto proto;
        private final FileDescriptor[] publicDependencies;
        private final ServiceDescriptor[] services;

        public interface InternalDescriptorAssigner {
            ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor);
        }

        public enum Syntax {
            UNKNOWN("unknown"),
            PROTO2("proto2"),
            PROTO3("proto3");
            
            private final String name;

            private Syntax(String str) {
                this.name = str;
            }
        }

        public DescriptorProtos$FileDescriptorProto toProto() {
            return this.proto;
        }

        public String getName() {
            return this.proto.getName();
        }

        public FileDescriptor getFile() {
            return this;
        }

        public String getFullName() {
            return this.proto.getName();
        }

        public String getPackage() {
            return this.proto.getPackage();
        }

        public DescriptorProtos$FileOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<Descriptor> getMessageTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
        }

        public List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public List<ServiceDescriptor> getServices() {
            return Collections.unmodifiableList(Arrays.asList(this.services));
        }

        public List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public List<FileDescriptor> getDependencies() {
            return Collections.unmodifiableList(Arrays.asList(this.dependencies));
        }

        public List<FileDescriptor> getPublicDependencies() {
            return Collections.unmodifiableList(Arrays.asList(this.publicDependencies));
        }

        public Syntax getSyntax() {
            if (Syntax.PROTO3.name.equals(this.proto.getSyntax())) {
                return Syntax.PROTO3;
            }
            return Syntax.PROTO2;
        }

        public Descriptor findMessageTypeByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (getPackage().length() > 0) {
                str = getPackage() + '.' + str;
            }
            GenericDescriptor findSymbol = this.pool.findSymbol(str);
            return (findSymbol != null && (findSymbol instanceof Descriptor) && findSymbol.getFile() == this) ? (Descriptor) findSymbol : null;
        }

        public EnumDescriptor findEnumTypeByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (getPackage().length() > 0) {
                str = getPackage() + '.' + str;
            }
            GenericDescriptor findSymbol = this.pool.findSymbol(str);
            return (findSymbol != null && (findSymbol instanceof EnumDescriptor) && findSymbol.getFile() == this) ? (EnumDescriptor) findSymbol : null;
        }

        public ServiceDescriptor findServiceByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (getPackage().length() > 0) {
                str = getPackage() + '.' + str;
            }
            GenericDescriptor findSymbol = this.pool.findSymbol(str);
            return (findSymbol != null && (findSymbol instanceof ServiceDescriptor) && findSymbol.getFile() == this) ? (ServiceDescriptor) findSymbol : null;
        }

        public FieldDescriptor findExtensionByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (getPackage().length() > 0) {
                str = getPackage() + '.' + str;
            }
            GenericDescriptor findSymbol = this.pool.findSymbol(str);
            return (findSymbol != null && (findSymbol instanceof FieldDescriptor) && findSymbol.getFile() == this) ? (FieldDescriptor) findSymbol : null;
        }

        public static FileDescriptor buildFrom(DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto, FileDescriptor[] fileDescriptorArr) throws DescriptorValidationException {
            return buildFrom(descriptorProtos$FileDescriptorProto, fileDescriptorArr, false);
        }

        public static FileDescriptor buildFrom(DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto, FileDescriptor[] fileDescriptorArr, boolean z) throws DescriptorValidationException {
            FileDescriptor fileDescriptor = new FileDescriptor(descriptorProtos$FileDescriptorProto, fileDescriptorArr, new DescriptorPool(fileDescriptorArr, z), z);
            fileDescriptor.crossLink();
            return fileDescriptor;
        }

        public static void internalBuildGeneratedFileFrom(String[] strArr, FileDescriptor[] fileDescriptorArr, InternalDescriptorAssigner internalDescriptorAssigner) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String append : strArr) {
                stringBuilder.append(append);
            }
            byte[] bytes = stringBuilder.toString().getBytes(Internal.ISO_8859_1);
            try {
                try {
                    FileDescriptor buildFrom = buildFrom(DescriptorProtos$FileDescriptorProto.parseFrom(bytes), fileDescriptorArr, true);
                    ExtensionRegistryLite assignDescriptors = internalDescriptorAssigner.assignDescriptors(buildFrom);
                    if (assignDescriptors != null) {
                        try {
                            buildFrom.setProto(DescriptorProtos$FileDescriptorProto.parseFrom(bytes, assignDescriptors));
                        } catch (Throwable e) {
                            throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
                        }
                    }
                } catch (Throwable e2) {
                    throw new IllegalArgumentException("Invalid embedded descriptor for \"" + r1.getName() + "\".", e2);
                }
            } catch (Throwable e22) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e22);
            }
        }

        public static void internalBuildGeneratedFileFrom(String[] strArr, Class<?> cls, String[] strArr2, String[] strArr3, InternalDescriptorAssigner internalDescriptorAssigner) {
            List arrayList = new ArrayList();
            for (int i = 0; i < strArr2.length; i++) {
                try {
                    arrayList.add((FileDescriptor) cls.getClassLoader().loadClass(strArr2[i]).getField("descriptor").get(null));
                } catch (Exception e) {
                    Descriptors.logger.warning("Descriptors for \"" + strArr3[i] + "\" can not be found.");
                }
            }
            FileDescriptor[] fileDescriptorArr = new FileDescriptor[arrayList.size()];
            arrayList.toArray(fileDescriptorArr);
            internalBuildGeneratedFileFrom(strArr, fileDescriptorArr, internalDescriptorAssigner);
        }

        public static void internalUpdateFileDescriptor(FileDescriptor fileDescriptor, ExtensionRegistry extensionRegistry) {
            try {
                fileDescriptor.setProto(DescriptorProtos$FileDescriptorProto.parseFrom(fileDescriptor.proto.toByteString(), extensionRegistry));
            } catch (Throwable e) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
            }
        }

        private FileDescriptor(DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto, FileDescriptor[] fileDescriptorArr, DescriptorPool descriptorPool, boolean z) throws DescriptorValidationException {
            int publicDependency;
            int i;
            int i2;
            this.pool = descriptorPool;
            this.proto = descriptorProtos$FileDescriptorProto;
            this.dependencies = (FileDescriptor[]) fileDescriptorArr.clone();
            HashMap hashMap = new HashMap();
            for (FileDescriptor fileDescriptor : fileDescriptorArr) {
                hashMap.put(fileDescriptor.getName(), fileDescriptor);
            }
            List arrayList = new ArrayList();
            for (i = 0; i < descriptorProtos$FileDescriptorProto.getPublicDependencyCount(); i++) {
                publicDependency = descriptorProtos$FileDescriptorProto.getPublicDependency(i);
                if (publicDependency < 0 || publicDependency >= descriptorProtos$FileDescriptorProto.getDependencyCount()) {
                    throw new DescriptorValidationException(this, "Invalid public dependency index.");
                }
                String dependency = descriptorProtos$FileDescriptorProto.getDependency(publicDependency);
                FileDescriptor fileDescriptor2 = (FileDescriptor) hashMap.get(dependency);
                if (fileDescriptor2 != null) {
                    arrayList.add(fileDescriptor2);
                } else if (!z) {
                    throw new DescriptorValidationException(this, "Invalid public dependency: " + dependency);
                }
            }
            this.publicDependencies = new FileDescriptor[arrayList.size()];
            arrayList.toArray(this.publicDependencies);
            descriptorPool.addPackage(getPackage(), this);
            this.messageTypes = new Descriptor[descriptorProtos$FileDescriptorProto.getMessageTypeCount()];
            for (i2 = 0; i2 < descriptorProtos$FileDescriptorProto.getMessageTypeCount(); i2++) {
                this.messageTypes[i2] = new Descriptor(descriptorProtos$FileDescriptorProto.getMessageType(i2), this, null, i2);
            }
            this.enumTypes = new EnumDescriptor[descriptorProtos$FileDescriptorProto.getEnumTypeCount()];
            for (i2 = 0; i2 < descriptorProtos$FileDescriptorProto.getEnumTypeCount(); i2++) {
                this.enumTypes[i2] = new EnumDescriptor(descriptorProtos$FileDescriptorProto.getEnumType(i2), this, null, i2);
            }
            this.services = new ServiceDescriptor[descriptorProtos$FileDescriptorProto.getServiceCount()];
            for (publicDependency = 0; publicDependency < descriptorProtos$FileDescriptorProto.getServiceCount(); publicDependency++) {
                this.services[publicDependency] = new ServiceDescriptor(descriptorProtos$FileDescriptorProto.getService(publicDependency), this, publicDependency);
            }
            this.extensions = new FieldDescriptor[descriptorProtos$FileDescriptorProto.getExtensionCount()];
            for (i2 = 0; i2 < descriptorProtos$FileDescriptorProto.getExtensionCount(); i2++) {
                this.extensions[i2] = new FieldDescriptor(descriptorProtos$FileDescriptorProto.getExtension(i2), this, null, i2, true);
            }
        }

        FileDescriptor(String str, Descriptor descriptor) throws DescriptorValidationException {
            this.pool = new DescriptorPool(new FileDescriptor[0], true);
            this.proto = DescriptorProtos$FileDescriptorProto.newBuilder().setName(descriptor.getFullName() + ".placeholder.proto").setPackage(str).addMessageType(descriptor.toProto()).build();
            this.dependencies = new FileDescriptor[0];
            this.publicDependencies = new FileDescriptor[0];
            this.messageTypes = new Descriptor[]{descriptor};
            this.enumTypes = new EnumDescriptor[0];
            this.services = new ServiceDescriptor[0];
            this.extensions = new FieldDescriptor[0];
            this.pool.addPackage(str, this);
            this.pool.addSymbol(descriptor);
        }

        private void crossLink() throws DescriptorValidationException {
            int i = 0;
            for (Descriptor access$700 : this.messageTypes) {
                access$700.crossLink();
            }
            for (ServiceDescriptor access$800 : this.services) {
                access$800.crossLink();
            }
            FieldDescriptor[] fieldDescriptorArr = this.extensions;
            int length = fieldDescriptorArr.length;
            while (i < length) {
                fieldDescriptorArr[i].crossLink();
                i++;
            }
        }

        private void setProto(DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto) {
            int i;
            int i2 = 0;
            this.proto = descriptorProtos$FileDescriptorProto;
            for (i = 0; i < this.messageTypes.length; i++) {
                this.messageTypes[i].setProto(descriptorProtos$FileDescriptorProto.getMessageType(i));
            }
            for (i = 0; i < this.enumTypes.length; i++) {
                this.enumTypes[i].setProto(descriptorProtos$FileDescriptorProto.getEnumType(i));
            }
            for (i = 0; i < this.services.length; i++) {
                this.services[i].setProto(descriptorProtos$FileDescriptorProto.getService(i));
            }
            while (i2 < this.extensions.length) {
                this.extensions[i2].setProto(descriptorProtos$FileDescriptorProto.getExtension(i2));
                i2++;
            }
        }

        boolean supportsUnknownEnumValue() {
            return getSyntax() == Syntax.PROTO3;
        }
    }

    public static final class MethodDescriptor extends GenericDescriptor {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private Descriptor inputType;
        private Descriptor outputType;
        private DescriptorProtos$MethodDescriptorProto proto;
        private final ServiceDescriptor service;

        public int getIndex() {
            return this.index;
        }

        public DescriptorProtos$MethodDescriptorProto toProto() {
            return this.proto;
        }

        public String getName() {
            return this.proto.getName();
        }

        public String getFullName() {
            return this.fullName;
        }

        public FileDescriptor getFile() {
            return this.file;
        }

        public ServiceDescriptor getService() {
            return this.service;
        }

        public Descriptor getInputType() {
            return this.inputType;
        }

        public Descriptor getOutputType() {
            return this.outputType;
        }

        public DescriptorProtos$MethodOptions getOptions() {
            return this.proto.getOptions();
        }

        private MethodDescriptor(DescriptorProtos$MethodDescriptorProto descriptorProtos$MethodDescriptorProto, FileDescriptor fileDescriptor, ServiceDescriptor serviceDescriptor, int i) throws DescriptorValidationException {
            this.index = i;
            this.proto = descriptorProtos$MethodDescriptorProto;
            this.file = fileDescriptor;
            this.service = serviceDescriptor;
            this.fullName = serviceDescriptor.getFullName() + '.' + descriptorProtos$MethodDescriptorProto.getName();
            fileDescriptor.pool.addSymbol(this);
        }

        private void crossLink() throws DescriptorValidationException {
            GenericDescriptor lookupSymbol = this.file.pool.lookupSymbol(this.proto.getInputType(), this, SearchFilter.TYPES_ONLY);
            if (lookupSymbol instanceof Descriptor) {
                this.inputType = (Descriptor) lookupSymbol;
                lookupSymbol = this.file.pool.lookupSymbol(this.proto.getOutputType(), this, SearchFilter.TYPES_ONLY);
                if (lookupSymbol instanceof Descriptor) {
                    this.outputType = (Descriptor) lookupSymbol;
                    return;
                }
                throw new DescriptorValidationException((GenericDescriptor) this, '\"' + this.proto.getOutputType() + "\" is not a message type.");
            }
            throw new DescriptorValidationException((GenericDescriptor) this, '\"' + this.proto.getInputType() + "\" is not a message type.");
        }

        private void setProto(DescriptorProtos$MethodDescriptorProto descriptorProtos$MethodDescriptorProto) {
            this.proto = descriptorProtos$MethodDescriptorProto;
        }
    }

    public static final class OneofDescriptor {
        private Descriptor containingType;
        private int fieldCount;
        private FieldDescriptor[] fields;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos$OneofDescriptorProto proto;

        public int getIndex() {
            return this.index;
        }

        public String getName() {
            return this.proto.getName();
        }

        public FileDescriptor getFile() {
            return this.file;
        }

        public String getFullName() {
            return this.fullName;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public int getFieldCount() {
            return this.fieldCount;
        }

        public DescriptorProtos$OneofOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<FieldDescriptor> getFields() {
            return Collections.unmodifiableList(Arrays.asList(this.fields));
        }

        public FieldDescriptor getField(int i) {
            return this.fields[i];
        }

        private void setProto(DescriptorProtos$OneofDescriptorProto descriptorProtos$OneofDescriptorProto) {
            this.proto = descriptorProtos$OneofDescriptorProto;
        }

        private OneofDescriptor(DescriptorProtos$OneofDescriptorProto descriptorProtos$OneofDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i) throws DescriptorValidationException {
            this.proto = descriptorProtos$OneofDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, descriptorProtos$OneofDescriptorProto.getName());
            this.file = fileDescriptor;
            this.index = i;
            this.containingType = descriptor;
            this.fieldCount = 0;
        }
    }

    public static final class ServiceDescriptor extends GenericDescriptor {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private MethodDescriptor[] methods;
        private DescriptorProtos$ServiceDescriptorProto proto;

        public int getIndex() {
            return this.index;
        }

        public DescriptorProtos$ServiceDescriptorProto toProto() {
            return this.proto;
        }

        public String getName() {
            return this.proto.getName();
        }

        public String getFullName() {
            return this.fullName;
        }

        public FileDescriptor getFile() {
            return this.file;
        }

        public DescriptorProtos$ServiceOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<MethodDescriptor> getMethods() {
            return Collections.unmodifiableList(Arrays.asList(this.methods));
        }

        public MethodDescriptor findMethodByName(String str) {
            GenericDescriptor findSymbol = this.file.pool.findSymbol(this.fullName + '.' + str);
            if (findSymbol == null || !(findSymbol instanceof MethodDescriptor)) {
                return null;
            }
            return (MethodDescriptor) findSymbol;
        }

        private ServiceDescriptor(DescriptorProtos$ServiceDescriptorProto descriptorProtos$ServiceDescriptorProto, FileDescriptor fileDescriptor, int i) throws DescriptorValidationException {
            this.index = i;
            this.proto = descriptorProtos$ServiceDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, null, descriptorProtos$ServiceDescriptorProto.getName());
            this.file = fileDescriptor;
            this.methods = new MethodDescriptor[descriptorProtos$ServiceDescriptorProto.getMethodCount()];
            for (int i2 = 0; i2 < descriptorProtos$ServiceDescriptorProto.getMethodCount(); i2++) {
                this.methods[i2] = new MethodDescriptor(descriptorProtos$ServiceDescriptorProto.getMethod(i2), fileDescriptor, this, i2);
            }
            fileDescriptor.pool.addSymbol(this);
        }

        private void crossLink() throws DescriptorValidationException {
            for (MethodDescriptor access$2900 : this.methods) {
                access$2900.crossLink();
            }
        }

        private void setProto(DescriptorProtos$ServiceDescriptorProto descriptorProtos$ServiceDescriptorProto) {
            this.proto = descriptorProtos$ServiceDescriptorProto;
            for (int i = 0; i < this.methods.length; i++) {
                this.methods[i].setProto(descriptorProtos$ServiceDescriptorProto.getMethod(i));
            }
        }
    }

    private static String computeFullName(FileDescriptor fileDescriptor, Descriptor descriptor, String str) {
        if (descriptor != null) {
            return descriptor.getFullName() + '.' + str;
        }
        if (fileDescriptor.getPackage().length() > 0) {
            return fileDescriptor.getPackage() + '.' + str;
        }
        return str;
    }
}
