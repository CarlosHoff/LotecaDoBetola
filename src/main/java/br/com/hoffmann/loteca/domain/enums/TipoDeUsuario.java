package br.com.hoffmann.loteca.domain.enums;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public enum TipoDeUsuario {

    ADMIN(1, "Admin"),
    SUPORTE(2, "Suporte"),
    CLIENTE(3, "Cliente");

    private static final Map<Integer, TipoDeUsuario> TIPO_DE_USUARIO_MAP = new HashMap<>(values().length);

    static {
        TIPO_DE_USUARIO_MAP.putAll(stream(values()).collect(toMap(TipoDeUsuario::code, identity())));
    }

    private final Integer code;
    private final String description;

    TipoDeUsuario(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer code() {
        return this.code;
    }

    public Long getCode() {
        return code.longValue();
    }

    public String description() {
        return this.description;
    }

    public static TipoDeUsuario of(Long enumCode) {
        if (enumCode.equals(ADMIN.getCode())) {
            return ADMIN;
        } else if(enumCode.equals(SUPORTE.getCode())){
            return SUPORTE;
        } else if (enumCode.equals(CLIENTE.getCode())) {
            return CLIENTE;
        } else
            return null;
    }
}
