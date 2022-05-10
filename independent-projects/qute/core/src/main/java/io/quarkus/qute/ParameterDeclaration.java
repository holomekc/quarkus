package io.quarkus.qute;

public interface ParameterDeclaration {

    String getTypeInfo();

    String getKey();

    Expression getDefaultValue();

}
