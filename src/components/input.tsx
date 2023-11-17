import React from "react";
import { Field, ErrorMessage, FieldProps } from "formik";
import styled from "styled-components";

const FieldStyled = styled.input`
  padding: 10px;
  border-radius: 5px;
  border: 1px solid;
  outline: none;
  font-size: 18px;
  width: 90%;
`;

const ErrorStyled = styled.span`
  color: red;
  font-size: 14px;
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  gap: 2px;
  margin-bottom: 15px;
  width: 100%;
`;

const Label = styled.label`
  text-transform: capitalize;
`;

const RequiredLabel = styled.span`
  color: red;
`;

interface InputProps {
  name: string;
  type?: string;
  label?: string;
  required?: boolean;
}

export const Input: React.FC<InputProps> = ({ name, type = "", label, required, ...props }) => {
  return (
    <Container>
      <Label>
        {label || name}
        {required && <RequiredLabel>*</RequiredLabel>}
      </Label>
      <Field name={name}>
        {({ field }: FieldProps) => (
          <FieldStyled
            {...field}
            name={name}
            type={type}
            {...props}
          />
        )}
      </Field>
      <ErrorMessage name={name} component={ErrorStyled} />
    </Container>
  );
};
