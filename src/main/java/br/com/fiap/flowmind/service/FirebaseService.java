package br.com.fiap.flowmind.service;

import org.springframework.stereotype.Service;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import br.com.fiap.flowmind.model.CheckinDiario;
import br.com.fiap.flowmind.model.RotinaDiaria;

import java.util.HashMap;
import java.util.Map;

@Service
public class FirebaseService {

    private final Firestore db = FirestoreClient.getFirestore();

    public void syncCheckin(CheckinDiario checkin) {

        String userId = checkin.getUsuario().getId().toString();
        String checkinId = checkin.getId().toString();

        Map<String, Object> data = new HashMap<>();
        data.put("id", checkin.getId());
        data.put("usuarioId", checkin.getUsuario().getId());
        data.put("humor", checkin.getHumor());
        data.put("energia", checkin.getEnergia());
        data.put("sono", checkin.getSono());
        data.put("dataCheckin", checkin.getDataCheckin().toString()); // <-- STRING

        db.collection("checkins")
                .document(userId)
                .collection("registros")
                .document(checkinId)
                .set(data);

        System.out.println("Check-in enviado ao Firestore!");
    }

    public void syncRotina(RotinaDiaria rotina) {

        String userId = rotina.getUsuario().getId().toString();
        String data = rotina.getDataRotina().toString();

        Map<String, Object> map = new HashMap<>();
        map.put("usuarioId", rotina.getUsuario().getId());
        map.put("data", rotina.getDataRotina().toString());
        map.put("rotina", rotina.getRotinaJson());

        db.collection("rotinas")
                .document(userId)
                .collection("dias")
                .document(data)
                .set(map);

        System.out.println("Rotina enviada ao Firestore!");
    }
}
