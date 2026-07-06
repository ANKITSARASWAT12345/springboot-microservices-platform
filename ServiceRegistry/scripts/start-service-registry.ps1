param(
    [int]$Port = 8761
)

$ErrorActionPreference = "Stop"

$projectRoot = Split-Path -Parent $PSScriptRoot
$stopScript = Join-Path $PSScriptRoot "stop-service-registry.ps1"

& $stopScript -Port $Port

# The Maven wrapper needs these directories in PATH when launched from some IDE terminals.
$requiredPathEntries = @(
    "C:\Windows\System32",
    "C:\Windows\System32\WindowsPowerShell\v1.0"
)

foreach ($entry in $requiredPathEntries) {
    if ($env:Path -notlike "*$entry*") {
        $env:Path = "$entry;$env:Path"
    }
}

Set-Location $projectRoot
Write-Host "Starting ServiceRegistry on port $Port..."
& "$projectRoot\mvnw.cmd" spring-boot:run

