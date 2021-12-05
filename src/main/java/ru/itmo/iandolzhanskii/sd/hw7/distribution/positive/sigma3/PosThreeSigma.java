package ru.itmo.iandolzhanskii.sd.hw7.distribution.positive.sigma3;

import org.springframework.stereotype.Component;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.Sigma;

@Component
public class PosThreeSigma implements Sigma {
    
    public void accept() {
        System.out.println("[2s, 3s)");
    }
}
