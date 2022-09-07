package ru.kovalchuk.user.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DeleteUserRequest {

        @NotBlank
        @Length(max = 10)
        private Long id;

    }
