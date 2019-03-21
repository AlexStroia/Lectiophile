package co.alexdev.data.helper;

public interface ResultMapper<Parameter, Result> {

    Result map(Parameter parameter);
}
