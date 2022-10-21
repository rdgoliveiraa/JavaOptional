package com.roalmeida.optional;

import java.util.Optional;

public class OrElseAndOrElseGetDiffer {


    /**
     * No exemplo acima, não estamos mais envolvendo um valor nulo e o restante do código permanece o mesmo.
     *
     * Agora vamos dar uma olhada no efeito colateral de executar este código:
     *
     * Using orElseGet:
     * Using orElse:
     * Getting default value...
     * Observe que ao usar orElseGet() para recuperar o valor encapsulado, o método getMyDefault() nem é invocado, pois o valor contido está presente.
     *
     * No entanto, ao usar orElse() , independentemente de o valor encapsulado estar presente ou não, o objeto padrão é criado.
     * Portanto, neste caso, acabamos de criar um objeto redundante que nunca é usado.
     *
     * Neste exemplo simples, não há custo significativo para criar um objeto padrão, pois a JVM sabe como lidar com isso.
     * No entanto, quando um método como getMyDefault() precisa fazer uma chamada de serviço web ou até mesmo consultar um banco de dados, o custo se torna muito óbvio.
     */
    public void whenOrElseGetAndOrElseDiffer() {
        String text = "Text present";
        System.out.println("Using orElseGet:");
        String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        System.out.println("Text present".equals(defaultText));
        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        System.out.println("Text present".equals(defaultText));
    }

    public String getMyDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }
}
