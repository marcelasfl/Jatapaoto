package com.example.geradorus.controller;

import com.example.geradorus.model.Epico;
import com.example.geradorus.model.HistoriaUsuario;
import com.example.geradorus.model.TipoUS;
import com.example.geradorus.repository.EpicoRepository;
import com.example.geradorus.repository.HistoriaUsuarioRepository;
import com.example.geradorus.repository.TipoUSRepository;
//import com.sun.tools.javac.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import static com.sun.tools.javac.util.StringUtils.toUpperCase;

@RestController
@RequestMapping("/api/us")
public class HistoriaUsuarioController {

    private List<List<Epico>> epicoID = new ArrayList<List<Epico>>();
    @Autowired
    EpicoRepository epicoRepository;
    @Autowired
    TipoUSRepository tipoUSRepository;
    @Autowired
    HistoriaUsuarioRepository historiaUsuarioRepository;



    //Get para pegar do epico pelo id
    @GetMapping("/{id}")
    public ResponseEntity<List<HistoriaUsuario>> gerarHistoriaUsuario(@PathVariable(value="id") long id){

       String[] epics; //Criando um array de strings


        Optional<Epico> epicos = epicoRepository.findById(id);
        List<TipoUS> tipoUS = tipoUSRepository.findAll();



        for (int i = 0; i < tipoUS.size(); i++) {
            //if(epicos.get().getId() == tipoUS.get(i).){
            String descricaoEpic = epicos.get().getDescricao(); //Resgatando a descrição


            epics = descricaoEpic.split(" "); //Separando a String em elementos de um array atraves de um espaço
            for (int s = 0; s < epics.length; s++) {
                if (epics[s].equals("desejo")) { //Achando a posição do elemento desejo
                    epics[s + 1] = String.valueOf(tipoUS.get(i).getDescricao()); //Trocando os valores do elemento com os verbos
                    String palavra = String.join(" ", epics); //Transformando o array em uma String

                    saveUS(epicos.get().getCategoria(), palavra, epicos.get().getRelevancia(), epicos.get().getTitulo()); //Parmetros para salvar no bd
                }
            }

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(historiaUsuarioRepository.findAll());
    }

    private void saveUS(String categoria, String descricao, String relevancia, String titulo) { //Função para salvar no bd
        HistoriaUsuario historiaUsuario = new HistoriaUsuario();

        historiaUsuario.setCategoria(categoria);
        historiaUsuario.setDescricao(descricao);
        historiaUsuario.setRelevancia(relevancia);
        historiaUsuario.setTitulo(titulo);
        //historiaUsuario.setEpico(epico);

        historiaUsuarioRepository.save(historiaUsuario);
    }


}
