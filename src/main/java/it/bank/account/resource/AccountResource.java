package it.bank.account.resource;

import it.bank.account.service.AccountService;
import it.bank.account.domain.vo.Account;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Formatter;
import java.util.Objects;
import java.util.Optional;

@Path("/accounts")
@ApplicationScoped
public class AccountResource {
    @Inject
    private AccountService accountService;

    @Inject
    private Logger LOGGER;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allAccount(){
        return Response.ok(accountService.viewAccountList()).build();

    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("accountNumber") Long accountNumber){
        LOGGER.debug(MessageFormat.format("GET /accounts/{0}",  accountNumber));
        Optional<Account> accountOpt;
        accountOpt = accountService.viewAccountDetails(accountNumber);
        return Response.ok((accountOpt.isPresent()) ? accountOpt.get() : null).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Account account, @Context UriInfo uriInfo, @Context HttpHeaders httpHeaders ) throws URISyntaxException {
        LOGGER.debug(MessageFormat.format("POST /accounts account number {0}",  (Objects.nonNull(account)? account.getAccountNumber(): null)));
        Account accountSaved = accountService.addAccountToBankAccount(account);
        return Response.created(new URI(uriInfo.getPath()+'/'+account.getAccountNumber())).entity(accountSaved).build();
    }

    @DELETE
    @Path("/{accountNumber}")
    public Response delete(@PathParam("accountNumber") Long accountNumber){
        LOGGER.debug(MessageFormat.format("DELETE /accounts/{0}",  accountNumber));
        accountService.removeAccountFromBankAccount(accountNumber);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{accountNumber}")
    public <T extends Account> Response put(@PathParam("accountNumber") Long accountNumber, Account account,@Context HttpHeaders httpHeaders ){
        LOGGER.debug(MessageFormat.format("PUT /accounts/{0}",  accountNumber));
        Account accountUpdated = accountService.editAccountDetail(accountNumber,account);
       return Response.ok(accountUpdated).build();
    }

}
