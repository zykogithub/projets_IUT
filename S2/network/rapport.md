# Rapport S203

- [Rapport S203](#rapport-s203)
  - [Configuration des machines et routes Statiques](#configuration-des-machines-et-routes-statiques)
    - [Configuration statique](#configuration-statique)

## Configuration des machines et routes Statiques

### Configuration statique

Configuration des machines de mannière péreinne :

- porthos

```bash
    auto eth0
    iface eth0 inet static
    address 10.24.0.1/14

    auto eth1
    iface eth1 inet static
    address 10.0.193.2/24
    gateway 10.0.193.1
```
