package br.com.crud.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produto")
@Getter @Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Produto extends PanacheEntity {

      @NonNull
      private String nome;

      @NonNull
      private BigDecimal valor;

      @CreationTimestamp
      private OffsetDateTime dataCriacao;

      @UpdateTimestamp
      private OffsetDateTime dataAtualizacao;
      
}