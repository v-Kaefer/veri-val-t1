package com.example;

import java.time.LocalTime;

interface Estacionamento {
    static final double VALOR_FIXO = 5.90;
    static final double VALOR_POR_HORA = 2.50;
    static final double VALOR_PERNOITE = 50.00;
    static final int CORTESIA = 15;
    static final LocalTime HORARIO_ABERTURA = LocalTime.of(8, 0);
    static final LocalTime HORARIO_FECHAMENTO = LocalTime.of(2, 0);
}
