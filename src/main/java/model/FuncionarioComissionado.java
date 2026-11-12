package model;

public class FuncionarioComissionado extends Funcionario implements Tributavel {

    private double vendas;
    private double percentualComissao;

    public FuncionarioComissionado(int id, String nome, String cpf,
                                   double salarioBase, double vendas,
                                   double percentualComissao) {

        super(id, nome, cpf, salarioBase);

        this.vendas = vendas;
        this.percentualComissao = percentualComissao;
    }

    public double getVendas() {
        return vendas;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    @Override
    public double calcularTributavel() {
        double remuneracao = getSalarioBase() + vendas * (percentualComissao / 100);

        return remuneracao * 0.10;
    }

    @Override
    public double calcularRemuneracao() {
        return this.getSalarioBase() + vendas * (percentualComissao / 100);
    }
}