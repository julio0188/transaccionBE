package com.transaccion.transaccion.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;

public class RegistroDto {
    @NotBlank
    private String descripcion;
    @Min(0)
    private float monto;
    @NotBlank
    private String fecha;
    @NotBlank
    private String estado;

    public RegistroDto() {
    }

    public RegistroDto(String descripcion, float monto, String fecha, String estado) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
