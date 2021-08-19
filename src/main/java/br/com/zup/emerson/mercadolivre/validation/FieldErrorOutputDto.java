package br.com.zup.emerson.mercadolivre.validation;

public class FieldErrorOutputDto {
    private String field;
    private String message;

    public FieldErrorOutputDto() { }

    public FieldErrorOutputDto(String field, String message) {
        this.field = field;
        this.message= message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
