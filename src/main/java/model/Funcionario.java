package model;

public abstract class Funcionario implements Identificavel {

    private int id;
    private String nome;
    private String cpf;
    private double salarioBase;

    public Funcionario(int id, String nome, String cpf, double salarioBase) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        if (!isValidCPF(cpf)) {
            throw new IllegalArgumentException("Invalid CPF.");
        }

        if (salarioBase < 0 || !Double.isFinite(salarioBase)) {
            throw new IllegalArgumentException("Base salary cannot be negative or invalid.");
        }

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.salarioBase = salarioBase;
    }

    private boolean isValidCPF(String cpf) {

        if (cpf == null) {
            return false;
        }

        String digits = cpf.replaceAll("\\D", "");

        if (digits.length() != 11) {
            return false;
        }

        // Reject CPFs containing the same digit repeatedly.
        if (digits.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;

        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(digits.charAt(i)) * (10 - i);
        }

        int firstDigit = (sum * 10) % 11;

        if (firstDigit == 10) {
            firstDigit = 0;
        }

        if (firstDigit != Character.getNumericValue(digits.charAt(9))) {
            return false;
        }

        sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(digits.charAt(i)) * (11 - i);
        }

        int secondDigit = (sum * 10) % 11;

        if (secondDigit == 10) {
            secondDigit = 0;
        }

        return secondDigit == Character.getNumericValue(digits.charAt(10));
    }

    @Override
    public int getId() {
        return id;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public abstract double calcularRemuneracao();
}