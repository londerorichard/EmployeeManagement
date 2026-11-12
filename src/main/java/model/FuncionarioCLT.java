package model;

import java.util.HashMap;

public class FuncionarioCLT extends Funcionario implements Tributavel {

    private HashMap<String, Double> beneficios;

    public FuncionarioCLT(int id, String nome, String cpf,
                          double salarioBase, HashMap<String, Double> beneficios) {

        super(id, nome, cpf, salarioBase);

//        Initializes with an empty array if it has no beneficios
        this.beneficios = (beneficios == null)
                ? new HashMap<>()
                : new HashMap<>(beneficios);
    }

    public HashMap<String, Double> getBeneficios() {
        return new HashMap<>(beneficios);
    }

    @Override
    public double calcularTributavel() {
        return getSalarioBase() * 0.10;
    }

    @Override
    public double calcularRemuneracao() {
        double totalBenefits = this.getBeneficios()
                .values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        return this.getSalarioBase() + totalBenefits;
    }
}