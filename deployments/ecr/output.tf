output "repository_url" {
  description = "The URL of the created repository"
  value       = aws_ecr_repository.repository.repository_url
}