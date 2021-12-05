package ru.itmo.iandolzhanskii.sd.hw7.distribution.positive.sigma1;

import org.springframework.stereotype.Component;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.Sigma;

@Component
public class PosOneSigma implements Sigma {
    
    public void accept() {
        System.out.println("[0, 1s)");
    }
}
