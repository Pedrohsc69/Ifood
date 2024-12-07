package Entity;

import java.sql.Time;

public class Horario_Funcionamento extends Base{

    private String diaSemana;
    private Time horaAbertura;
    private Time horaFechamento;
    private Restaurante restaurante;



    public String getDiaSemana() {
        return diaSemana;
    }
    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }


    public Time getHoraAbertura() {
        return horaAbertura;
    }
    public void setHoraAbertura(Time horaAbertura) {
        this.horaAbertura = horaAbertura;
    }


    public Time getHoraFechamento() {
        return horaFechamento;
    }
    public void setHoraFechamento(Time horaFechamento) {
        this.horaFechamento = horaFechamento;
    }


    public Restaurante getRestaurante() {
        return restaurante;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
