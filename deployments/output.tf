#
#output "uri_name_invoke" {
#  description = "Uri Lambda function."
#  value = aws_lambda_function.auth_fiap_food.invoke_arn
#}
#
#output "resor_arn" {
#  description = "Permission"
#  value = aws_lambda_permission.apigw_lambda_token.source_arn
#}
#
#output "cog_client_id" {
#  description = "Client ID do Cog"
#  value = aws_cognito_user_pool_client.cognito_user_pool_client.id
#}