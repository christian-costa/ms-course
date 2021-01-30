package br.com.saves.hrpayroll.services;

import br.com.saves.hrpayroll.entities.Payment;
import br.com.saves.hrpayroll.entities.Worker;
import br.com.saves.hrpayroll.feingclients.WorkerFeingClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {

    private WorkerFeingClient workerFeingClient;

    public Payment getPayment(long workerId, int days) {

        Worker worker = workerFeingClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

}
