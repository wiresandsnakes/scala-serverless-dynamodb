package inputform.backend.handler

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.config.{Initializer, ListItemsModule}
import inputform.backend.service.{ExecutionStatusResolver, Service}

class ListItemsHandler extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] with Initializer {
  def handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    ExecutionStatusResolver.resolveStatus({
      val service: Service = initialize(new ListItemsModule)
      service.execute(request, context)
    })
  }
}