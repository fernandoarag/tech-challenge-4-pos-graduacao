package br.com.fiap.postech.products.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UpdateProductStockRequest
 */
@SuppressWarnings({"hiding", "static-method", "unused"})
@lombok.NoArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.8.0")
public class UpdateProductStockRequest {

  private Long productId;

  private Integer quantity;

  /**
   * Constructor with only required parameters
   */
  public UpdateProductStockRequest(Long productId, Integer quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public UpdateProductStockRequest productId(Long productId) {
    this.productId = productId;
    return this;
  }

  /**
   * ID of the product.
   * minimum: 1
   * @return productId
   */
  @NotNull @Min(1L) 
  @Schema(name = "productId", description = "ID of the product.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("productId")
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public UpdateProductStockRequest quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Quantity to update the stock by.
   * minimum: 1
   * @return quantity
   */
  @NotNull @Min(1) 
  @Schema(name = "quantity", description = "Quantity to update the stock by.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("quantity")
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateProductStockRequest updateProductStockRequest = (UpdateProductStockRequest) o;
    return Objects.equals(this.productId, updateProductStockRequest.productId) &&
        Objects.equals(this.quantity, updateProductStockRequest.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateProductStockRequest {\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public static class Builder {

    private UpdateProductStockRequest instance;

    public Builder() {
      this(new UpdateProductStockRequest());
    }

    protected Builder(UpdateProductStockRequest instance) {
      this.instance = instance;
    }

    protected Builder copyOf(UpdateProductStockRequest value) { 
      this.instance.setProductId(value.productId);
      this.instance.setQuantity(value.quantity);
      return this;
    }

    public UpdateProductStockRequest.Builder productId(Long productId) {
      this.instance.productId(productId);
      return this;
    }
    
    public UpdateProductStockRequest.Builder quantity(Integer quantity) {
      this.instance.quantity(quantity);
      return this;
    }
    
    /**
    * returns a built UpdateProductStockRequest instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public UpdateProductStockRequest build() {
      try {
        return this.instance;
      } finally {
        // ensure that this.instance is not reused
        this.instance = null;
      }
    }

    @Override
    public String toString() {
      return getClass() + "=(" + instance + ")";
    }
  }

  /**
  * Create a builder with no initialized field (except for the default values).
  */
  public static UpdateProductStockRequest.Builder builder() {
    return new UpdateProductStockRequest.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public UpdateProductStockRequest.Builder toBuilder() {
    UpdateProductStockRequest.Builder builder = new UpdateProductStockRequest.Builder();
    return builder.copyOf(this);
  }

}

