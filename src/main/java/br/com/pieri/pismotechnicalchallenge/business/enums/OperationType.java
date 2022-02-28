package br.com.pieri.pismotechnicalchallenge.business.enums;

import java.util.HashMap;

public enum OperationType {
    COMPRA_A_VISTA(1),
    COMPRA_PARCELADA(2),
    SAQUE(3),
    PAGAMENTO(4);

    private Integer id;

    private static HashMap<Integer, OperationType> types = new HashMap<>();
    static {
        types.put(COMPRA_A_VISTA.id, COMPRA_A_VISTA);
        types.put(COMPRA_PARCELADA.id, COMPRA_PARCELADA);
        types.put(SAQUE.id, SAQUE);
        types.put(PAGAMENTO.id, PAGAMENTO);
    }

    public Integer getId() {
        return id;
    }

    OperationType(Integer id) {
        this.id = id;
    }

    public static OperationType getByid(Integer id){
        if(types.containsKey(id)){
            return types.get(id);
        }else{
            return null;
        }
    }
}
