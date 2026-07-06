param(
    [int]$Port = 8761
)

$ErrorActionPreference = "Stop"

$connections = Get-NetTCPConnection -LocalPort $Port -ErrorAction SilentlyContinue |
    Where-Object { $_.State -eq "Listen" }

if (-not $connections) {
    Write-Host "No process is listening on port $Port."
    exit 0
}

$processIds = $connections |
    Select-Object -ExpandProperty OwningProcess -Unique |
    Where-Object { $_ -and $_ -gt 0 }

foreach ($processId in $processIds) {
    $process = Get-Process -Id $processId -ErrorAction SilentlyContinue
    if (-not $process) {
        continue
    }

    $parentProcessId = (Get-CimInstance Win32_Process -Filter "ProcessId = $processId").ParentProcessId

    Write-Host "Stopping process $processId ($($process.ProcessName)) listening on port $Port."
    Stop-Process -Id $processId -Force

    while ($parentProcessId) {
        $parent = Get-Process -Id $parentProcessId -ErrorAction SilentlyContinue
        if (-not $parent -or $parent.ProcessName -notin @("cmd", "powershell", "pwsh")) {
            break
        }

        Write-Host "Stopping parent process $parentProcessId ($($parent.ProcessName))."
        Stop-Process -Id $parentProcessId -Force
        $parentProcessId = (Get-CimInstance Win32_Process -Filter "ProcessId = $parentProcessId" -ErrorAction SilentlyContinue).ParentProcessId
    }
}
