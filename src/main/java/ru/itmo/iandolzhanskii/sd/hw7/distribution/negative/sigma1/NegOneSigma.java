package ru.itmo.iandolzhanskii.sd.hw7.distribution.negative.sigma1;

import org.springframework.stereotype.Component;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.Sigma;

@Component
public class NegOneSigma implements Sigma {
    
    public void accept() {
        System.out.println("[-1s, 0)");
    }
}
