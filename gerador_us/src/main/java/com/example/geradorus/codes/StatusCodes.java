package com.example.geradorus.codes;

import lombok.Getter;

@Getter
public enum StatusCodes {
    EPIC_NOT_FOUND("Épico não encontrado no sistema."),
    EPIC_REMOVED("Épico removido com sucesso."),
    US_NOT_FOUND("História de usuário não encontrada no sistema."),
    US_REMOVED("História de usuário removida com sucesso."),
    PROJECT_NOT_FOUND("Projeto não encontrado no sistema."),
    PROJECT_REMOVED("Projeto removido com sucesso."),
    TASK_NOT_FOUND("Tarefa não encontrada no sistema."),
    TASK_REMOVED("Tarefa removida com sucesso."),
    EPIC_TYPE_NOT_FOUND("Tipo de épico não encontrado no sistema."),
    EPIC_TYPE_REMOVED("Tipo de épico removido com sucesso."),
    US_TYPE_NOT_FOUND("Tipo de história de usuário não encontrado no sistema."),
    US_TYPE_REMOVED("Tipo de história de usuário removido com sucesso."),
    TASK_TYPE_NOT_FOUND("Tipo de tarefa não encontrado no sistema."),
    TASK_TYPE_REMOVED("Tipo de tarefa removido com sucesso.");

    private final String code;
    StatusCodes(String code) {
        this.code = code;
    }

}
