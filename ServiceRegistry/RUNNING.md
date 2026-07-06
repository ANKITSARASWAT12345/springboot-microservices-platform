# Running ServiceRegistry locally

ServiceRegistry uses port `8761`. Only one copy can run at a time.

If IntelliJ shows:

```text
Web server failed to start. Port 8761 was already in use.
```

stop the existing registry first:

```powershell
.\scripts\stop-service-registry.ps1
```

Then start it from IntelliJ, or start it from PowerShell with:

```powershell
.\scripts\start-service-registry.ps1
```

The `Timer already cancelled` Eureka message is a shutdown side effect after the
real port conflict.

